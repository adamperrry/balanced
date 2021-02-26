<template>
  <div
    class="shadow-md max-w-xs bg-cyan-200 px-6 py-3 rounded-2xl flex flex-col"
  >
    <div class="flex justify-between font-semibold text-xl">
      <span class="text-warmGray-700">Liquid Balance: </span>
      <span :class="[liquidBalance >= 0 ? 'text-emerald-900' : 'text-red-800']">
        {{ toCurrency(liquidBalance) }}</span
      >
    </div>
    <div class="flex justify-between">
      <span class="text-warmGray-900">Total Balance: </span>
      <span :class="[totalBalance >= 0 ? 'text-emerald-900' : 'text-red-900']">
        {{ toCurrency(totalBalance) }}</span
      >
    </div>
    <span
      @click="viewAccounts()"
      class="text-warmGray-800 hover:text-warmGray-500 text-sm pt-2 underline cursor-pointer self-center "
      >View all acounts...</span
    >
  </div>
</template>

<script>
// @ is an alias to /src
import { mapGetters } from "vuex";

export default {
  name: "Liquidity",
  computed: {
    ...mapGetters(["accountBalances"]),
    liquidBalance() {
      return this.accountBalances.reduce(
        (total, account) =>
          account.isLiquid ? total + account.balance : total,
        0,
      );
    },
    totalBalance() {
      return this.accountBalances.reduce(
        (total, account) => total + account.balance,
        0,
      );
    },
  },
  methods: {
    viewAccounts() {
      this.$router.push({ path: "/accounts" });
    },
  },
};
</script>
