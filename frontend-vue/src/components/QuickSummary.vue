<template>
  <div>
    <div v-for="category in categoryExpenses" :key="category.id">
      <div>{{ category.name }}</div>
      <div>This Month: {{ toCurrency(category.thisMonth) }}</div>
      <div>Last Month: {{ toCurrency(category.lastMonth) }}</div>
      <br /><br />
    </div>

    <span>View all summaries...</span>
  </div>
</template>

<script>
// @ is an alias to /src
import { mapState } from "vuex";
import moment from "moment";

export default {
  name: "QuickSummary",
  computed: {
    ...mapState(["transactions"]),
    useMoment() {
      return moment();
    },
    categoryExpenses() {
      let expenses = {};
      let thisMonth = this.useMoment;
      let lastMonth = moment(thisMonth).subtract(1, "month");

      this.transactions.forEach((expense) => {
        if (expense.type.name !== "Expense") return;
        let category = expense.category.name;
        let date = moment(expense.date, "YYYY-MM-DD");

        let isThisMonth = date.isBetween(thisMonth, thisMonth, "month", "[]");
        let isLastMonth = date.isBetween(lastMonth, lastMonth, "month", "[]");
        if (!(isThisMonth || isLastMonth)) return;

        expenses[category] = expenses[category] || {
          name: category,
          id: expense.category.id,
          thisMonth: 0,
          lastMonth: 0,
        };

        if (isThisMonth) {
          expenses[category]["thisMonth"] += expense.amount;
        } else if (isLastMonth) {
          expenses[category]["lastMonth"] += expense.amount;
        }
      });

      let expenseArray = Object.values(expenses);
      expenseArray.sort((a, b) => (a.thisMonth < b.thisMonth ? 1 : -1));
      return expenseArray;
    },
  },
  methods: {
    toCurrency(n) {
      let formatter = new Intl.NumberFormat("en-us", {
        style: "currency",
        currency: "USD",
      });
      return formatter.format(n / 100);
    },
  },
};
</script>
