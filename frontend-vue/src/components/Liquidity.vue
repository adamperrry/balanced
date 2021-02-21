<template>
  <div>
    <div>
      <span>Liquid Balance:</span>
      <span> {{ toCurrency(liquidBalance) }}</span>
    </div>
    <div>
      <span>Total Balance:</span>
      <span> {{ toCurrency(totalBalance) }}</span>
    </div>
    <span>View all acounts...</span>
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
        0
      );
    },
    totalBalance() {
      return this.accountBalances.reduce(
        (total, account) => total + account.balance,
        0
      );
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
