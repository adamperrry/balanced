<template>
  <div
    class="flex flex-col shadow-md max-w-xs bg-orange-100 px-6 pb-3 pt-2 rounded-2xl "
  >
    <div class="flex justify-between">
      <div class="flex flex-col text-right justify-end pr-2">
        <span class="text-warmGray-700">{{ currentMonth }}:</span>
        <span class="text-warmGray-700">YTD: </span>
      </div>

      <div class="flex-grow flex flex-col text-right pr-2">
        <span
          class="text-lg font-semibold text-warmGray-700 border-b-2 border-orange-300 mb-1"
          >Income</span
        >
        <span class="text-emerald-900 font-semibold">
          {{ toCurrency(monthlyIncome) }}</span
        >
        <span class="text-emerald-900 font-semibold">
          {{ toCurrency(ytdIncome) }}</span
        >
      </div>

      <div class="flex-grow flex flex-col text-right">
        <span
          class="text-lg font-semibold text-warmGray-700 border-b-2 border-orange-300 mb-1"
          >Expenses</span
        >
        <span class="text-red-900 font-semibold">
          {{ toCurrency(monthlyExpenses) }}</span
        >
        <span class="text-red-900 font-semibold">
          {{ toCurrency(ytdExpenses) }}</span
        >
      </div>
    </div>

    <a
      @click="viewSummaries()"
      class="text-warmGray-700 mt-2 hover:text-warmGray-500 text-sm underline cursor-pointer text-center"
      >View all summaries...</a
    >
  </div>
</template>

<script>
// @ is an alias to /src
import { mapGetters } from "vuex";
import moment from "moment";
import { sumAmountsInMonth, sumAmountsAfterDate } from "@/store/helpers";

export default {
  name: "Totals",
  computed: {
    ...mapGetters(["expenses", "income"]),
    currentMonth() {
      return moment().format("MMMM");
    },
    useMoment() {
      return moment();
    },
    monthlyIncome() {
      let thisMonth = moment(this.useMoment);
      return sumAmountsInMonth(this.income, thisMonth);
    },
    monthlyExpenses() {
      let thisMonth = moment(this.useMoment);
      return sumAmountsInMonth(this.expenses, thisMonth) * -1;
    },
    ytdIncome() {
      let thisYear = moment(this.useMoment).startOf("year");
      return sumAmountsAfterDate(this.income, thisYear);
    },
    ytdExpenses() {
      let thisYear = moment(this.useMoment).startOf("year");
      return sumAmountsAfterDate(this.expenses, thisYear) * -1;
    },
  },
  methods: {
    viewSummaries() {
      this.$router.push({ path: "/summaries" });
    },
  },
};
</script>
