<template>
  <div
    class="cursor-pointer hover:relative hover:z-50 flex py-1 px-3 border-b border-gray-500 hover:shadow-md text-warmGray-800"
    :class="[
      {
        'bg-red-100': isExpense(transaction),
        'bg-white': isTransfer(transaction),
        'bg-emerald-50': isIncome(transaction),
      },
    ]"
  >
    <div class="px-1 w-col-1 flex-auto sm:flex-none">
      {{ dateFormat(transaction.date) }}
    </div>
    <div
      class="px-1 w-col-2 flex-auto sm:flex-none font-medium "
      :class="[
        {
          'text-red-900': isExpense(transaction),
          'text-emerald-900': isIncome(transaction),
        },
      ]"
    >
      {{ displayAmount(transaction) }}
    </div>
    <div class="px-1 w-col-3 hidden sm:block truncate">
      {{ transaction.category ? transaction.category.name : "-" }}
    </div>
    <div class="px-1 w-col-4 hidden lg:block truncate">
      {{ transaction.subcategory ? transaction.subcategory.name : "-" }}
    </div>
    <div class="px-1 w-col-5 hidden xl:block truncate">
      {{ transaction.toAccount ? transaction.toAccount.name : "-" }}
    </div>
    <div class="px-1 w-col-6 hidden xl:block truncate">
      {{ transaction.fromAccount ? transaction.fromAccount.name : "-" }}
    </div>
    <div class="px-1 w-col-7 hidden lg:block truncate">
      {{ transaction.method.name }}
    </div>
    <div class="px-1 w-col-8 flex-grow hidden sm:block">
      {{ transaction.description }}
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import moment from "moment";

export default {
  name: "TransactionRow",
  components: {},
  props: {
    transaction: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {};
  },
  computed: {},
  methods: {
    dateFormat(string) {
      return moment(string, "YYYY-MM-DD").format("MM/DD/YYYY");
    },
    displayAmount(transaction) {
      let amount = transaction.amount;
      amount = transaction.type.name === "Expense" ? amount * -1 : amount;
      return this.toCurrency(amount);
    },
    isExpense(transaction) {
      return transaction.type.name === "Expense";
    },
    isIncome(transaction) {
      return transaction.type.name === "Income";
    },
    isTransfer(transaction) {
      return transaction.type.name === "Fund Transfer";
    },
  },
};
</script>
