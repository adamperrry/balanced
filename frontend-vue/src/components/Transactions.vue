<template>
  <div class="pt-10 mb-12">
    <div
      class="flex pt-2 pb-1 px-3 rounded-t-xl bg-emerald-200 font-semibold tracking-wide border-b-4 border-emerald-600 shadow-sm"
    >
      <div class="w-col-1">Date</div>
      <div class="w-col-2">Amount</div>
      <div class="w-col-3 hidden sm:block">Category</div>
      <div class="w-col-4 hidden lg:block">Subcategory</div>
      <div class="w-col-5 hidden xl:block">To</div>
      <div class="w-col-6 hidden xl:block">From</div>
      <div class="w-col-7 hidden lg:block">Method</div>
      <div class="w-col-8 flex-grow hidden sm:block">Description</div>
    </div>

    <div
      v-for="transaction in shownTransactions"
      :key="transaction.id"
      class="flex py-1 px-3 border-b-2 hover:shadow-md"
    >
      <div class="w-col-1">Date</div>
      <div class="w-col-2">Amount</div>
      <div class="w-col-3 hidden sm:block truncate">Category</div>
      <div class="w-col-4 hidden lg:block truncate">Subcategory</div>
      <div class="w-col-5 hidden xl:block truncate">To</div>
      <div class="w-col-6 hidden xl:block truncate">From</div>
      <div class="w-col-7 hidden lg:block truncate">Method</div>
      <div class="w-col-8 flex-grow hidden sm:block">Description</div>
    </div>

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

export default {
  name: "Transactions",
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
    };
  },
  computed: {
    ...mapState(["transactions"]),
    shownTransactions() {
      if (this.showAll) return this.transactions;
      return this.transactions.slice(0, this.count);
    },
    showingAll() {
      return (
        this.showAll ||
        this.shownTransactions.length === this.transactions.length
      );
    },
  },
  methods: {
    showMore() {
      this.count += this.initialCount;
    },
    showLess() {
      this.count -= this.initialCount;
    },
    showInitial() {
      this.count = this.initialCount;
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
