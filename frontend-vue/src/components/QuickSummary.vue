<template>
  <div
    class=" bg-fuchsia-200 shadow-md max-w-4xl px-6 py-3 rounded-2xl flex flex-col items-center"
  >
    <div>
      <span class="text-fuchsia-900">Expenses At A Glance - </span>
      <span class="font-semibold text-xl text-warmGray-700">{{
        currentMonth
      }}</span>
    </div>

    <div class="flex flex-wrap justify-around">
      <div
        class="shadow-sm bg-fuchsia-300 rounded-xl mt-2 mx-1 px-4 pb-2 pt-1 flex-grow flex flex-col max-w-1xs transform hover:scale-97"
        v-for="category in categoryExpenses"
        :key="category.id"
      >
        <span
          class=" mb-1 text-white text-lg font-medium text-center tracking-wide border-b-2 border-fuchsia-200"
          >{{ category.name }}</span
        >

        <div class="flex items-end justify-between">
          <span class="text-base text-warmGray-800">This Month:</span>
          <span
            class=" font-medium"
            :class="[
              category.thisMonth >= category.lastMonth
                ? 'text-emerald-900'
                : 'text-red-900',
            ]"
            >{{ toCurrency(category.thisMonth) }}</span
          >
        </div>

        <div class="flex items-end justify-between text-sm text-warmGray-600">
          <span>Last Month:</span>
          <span>{{ toCurrency(category.lastMonth) }}</span>
        </div>
      </div>
    </div>

    <span
      @click="viewSummaries()"
      class="text-warmGray-700 mt-3 hover:text-warmGray-500 text-sm underline cursor-pointer "
      >View all summaries...</span
    >
  </div>
</template>

<script>
// @ is an alias to /src
import { mapState } from "vuex";
import moment from "moment";

export default {
  name: "QuickSummary",
  data() {
    return {};
  },
  computed: {
    ...mapState(["transactions"]),
    currentMonth() {
      return moment().format("MMMM, YYYY");
    },
    useMoment() {
      return moment();
    },
    categoryExpenses() {
      let expenses = {};
      let thisMonth = moment(this.useMoment);
      let lastMonth = moment(thisMonth).subtract(1, "month");

      this.transactions.forEach(expense => {
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
          expenses[category]["thisMonth"] -= expense.amount;
        } else if (isLastMonth) {
          expenses[category]["lastMonth"] -= expense.amount;
        }
      });

      let expenseArray = Object.values(expenses);
      expenseArray.sort((a, b) => (a.thisMonth > b.thisMonth ? 1 : -1));
      return expenseArray;
    },
  },
  methods: {
    viewSummaries() {
      this.$router.push({ path: "/summaries" });
    },
  },
};
</script>
