<template>
  <div class="pt-10 mb-12">
    <!-- start of header row -->
    <div
      class="flex pt-2 pb-1 px-3 rounded-t-xl bg-emerald-200 font-semibold tracking-wide border-b-4 border-emerald-600 shadow-sm text-warmGray-700"
    >
      <div class="px-1 w-col-1 flex-auto sm:flex-none">
        <div @click="dateSort()" class="flex items-center cursor-pointer">
          <span class="pr-1">Date</span>
          <SortIcon
            :class="[
              { hidden: !sortByDate },
              'transform',
              { 'rotate-180': sortByDate > 0 },
            ]"
            :classes="iconClasses"
          />
        </div>
      </div>
      <div class="px-1 w-col-2 flex-auto sm:flex-none">
        <div @click="amountSort()" class="flex items-center cursor-pointer">
          <span class="pr-1">Amount</span>
          <SortIcon
            :class="[
              { hidden: !sortByAmount },
              'transform',
              { 'rotate-180': sortByAmount > 0 },
            ]"
            :classes="iconClasses"
          />
        </div>
      </div>
      <div class="px-1 w-col-3 hidden sm:block">
        <div class="flex items-center">
          <span class="pr-1">Category</span>
          <FilterIcon :classes="iconClasses" />
        </div>
      </div>
      <div class="px-1 w-col-4 hidden lg:block">
        <div class="flex items-center">
          <span class="pr-1">Subcategory</span>
          <FilterIcon :classes="iconClasses" />
        </div>
      </div>
      <div class="px-1 w-col-5 hidden xl:block">
        <div class="flex items-center">
          <span class="pr-1">To</span>
          <FilterIcon :classes="iconClasses" />
        </div>
      </div>
      <div class="px-1 w-col-6 hidden xl:block">
        <div class="flex items-center">
          <span class="pr-1">From</span>
          <FilterIcon :classes="iconClasses" />
        </div>
      </div>
      <div class="px-1 w-col-7 hidden lg:block">
        <div class="flex items-center">
          <span class="pr-1">Method</span>
          <FilterIcon :classes="iconClasses" />
        </div>
      </div>
      <div class="px-1 w-col-8 flex-grow hidden sm:block">
        <div class="flex items-center">
          <span class="pr-1">Description</span>
          <FilterIcon :classes="iconClasses" />
        </div>
      </div>
    </div>
    <!-- end of header row -->

    <!-- start of table row -->
    <div
      v-for="transaction in shownTransactions"
      :key="transaction.id"
      class="cursor-pointer hover:relative hover:z-50 flex py-1 px-3 border-b-2 hover:shadow-md text-warmGray-800"
      :class="[
        {
          'bg-red-50': isExpense(transaction),
          'bg-blueGray-50': isTransfer(transaction),
          'bg-emerald-50': isIncome(transaction),
        },
      ]"
    >
      <div class="px-1 w-col-1 flex-auto sm:flex-none">
        {{ dateFormat(transaction.date) }}
      </div>
      <div
        class="pl-1 pr-6 w-col-2 flex-auto sm:flex-none font-medium text-right"
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
    <!-- end of table row -->

    <div class="flex justify-center">
      <!-- start of button -->
      <div
        @click="showLess()"
        title="Show 10 Less Records"
        :class="{
          hidden: shownTransactions.length <= initialCount || showingAll,
        }"
        class="relative transform scale-75 shadow-md cursor-pointer bg-rose-300 hover:bg-rose-400 w-14 h-14 rounded-full flex items-center justify-center"
      >
        <div
          class="w-7 h-7 border-l-6 border-t-6 border-white rounded-sm translate-y-1 transform rotate-45"
        ></div>
      </div>
      <!-- end of button -->
      <!-- start of button -->
      <div
        @click="showMore()"
        title="Show 10 More Records"
        :class="{ hidden: showingAll }"
        class="relative transform scale-75 shadow-md cursor-pointer bg-emerald-300 hover:bg-emerald-400 w-14 h-14 rounded-full flex items-center justify-center"
      >
        <div
          class="w-7 h-7 border-r-6 border-b-6 border-white rounded-sm -translate-y-1 transform rotate-45"
        ></div>
      </div>
      <!-- end of button -->
      <!-- start of button -->
      <div
        @click="showInitial()"
        title="Collapse All"
        :class="{
          hidden: shownTransactions.length <= initialCount || showingAll,
        }"
        class="relative transform -translate-x-2 scale-50 shadow-md cursor-pointer bg-trueGray-100 hover:bg-trueGray-200 w-14 h-14 rounded-full flex items-center justify-center"
      >
        <div
          class="w-7 h-7 border-l-5 border-t-5 border-trueGray-400 rounded-sm translate-y-1 transform rotate-45"
        ></div>
        <div
          class="absolute w-7 h-7 border-l-5 border-t-5 border-trueGray-400 rounded-sm translate-y-4 transform scale-75 rotate-45"
        ></div>
      </div>
      <!-- end of button -->
    </div>
    <!-- start of back to top -->
    <div
      @click="scrollToTop()"
      :class="{ hidden: shownTransactions.length <= initialCount }"
      class="text-center text-warmGray-700 mt-3 hover:text-warmGray-500 text-sm underline cursor-pointer "
    >
      Back to Top
    </div>
    <!-- end of back to top -->
  </div>
</template>

<script>
// @ is an alias to /src
import { mapState } from "vuex";
import moment from "moment";
import FilterIcon from "@/assets/FilterIcon.vue";
import SortIcon from "@/assets/SortIcon.vue";

export default {
  name: "Transactions",
  components: {
    FilterIcon,
    SortIcon,
  },
  props: {
    showAll: {
      type: Boolean,
      default: false,
    },
    initialCount: {
      type: Number,
      default: 10,
    },
  },
  data() {
    return {
      count: this.initialCount,
      iconClasses: ["w-3", "h-3", "text-emerald-700", "fill-current"],
      sortByDate: -1,
      sortByAmount: 0,
    };
  },
  computed: {
    ...mapState(["transactions"]),
    // first filter, then sort, then get shown transactions
    filteredTransactions() {
      let filteredTransactions = [...this.transactions];
      return filteredTransactions;
    },
    sortedTransactions() {
      let sortedTransactions = this.filteredTransactions;
      if (this.sortByDate) {
        sortedTransactions.sort((a, b) => {
          if (moment(a.date).isSameOrBefore(b.date))
            return this.sortByDate * -1;
          return this.sortByDate;
        });
      } else if (this.sortByAmount) {
        sortedTransactions.sort((a, b) => {
          if (a.amount < b.amount) return this.sortByAmount * -1;
          return this.sortByAmount;
        });
      }
      return sortedTransactions;
    },
    shownTransactions() {
      if (this.showAll) return this.sortedTransactions;
      return this.sortedTransactions.slice(0, this.count);
    },
    showingAll() {
      return (
        this.showAll ||
        this.shownTransactions.length === this.transactions.length
      );
    },
  },
  methods: {
    resetFilters() {
      // set sortByDate and sortByAmount, and clear filters
    },
    dateSort() {
      if (this.sortByAmount) {
        this.sortByAmount = 0;
        this.sortByDate = 1;
      }
      this.sortByDate = this.sortByDate * -1;
    },
    amountSort() {
      if (this.sortByDate) {
        this.sortByDate = 0;
        this.sortByAmount = 1;
      }
      this.sortByAmount = this.sortByAmount * -1;
    },
    showMore() {
      this.count += this.initialCount;
    },
    showLess() {
      this.count -= this.initialCount;
    },
    showInitial() {
      this.count = this.initialCount;
    },
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
  updated() {
    this.$nextTick(() => {
      if (this.showAll || this.shownTransactions.length === this.initialCount)
        return;
      this.scrollToBottom();
    });
  },
};
</script>
