<template>
  <div class="flex justify-center pt-10 ">
    <div
      class="flex flex-col flex-grow  max-w-7xl mx-3 divide-y-5 divide-dotted"
    >
      <div class="flex flex-col items-center sm:flex-row sm:items-start mb-10">
        <div class="flex flex-col flex-grow sm:mr-10 w-full max-w-xs">
          <Liquidity />
          <!-- Adding div to remove justify-between, so they don't get farther apart if QuickSummary gets taller -->
          <div class="mt-10"></div>
          <Totals />
          <div class="mt-10 sm:mt-0"></div>
        </div>
        <div class="flex flex-col flex-grow max-w-max">
          <QuickSummary />
        </div>
      </div>
      <!-- placeholder for transactions table -->
      <Transactions />
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Liquidity from "@/components/Liquidity.vue";
import QuickSummary from "@/components/QuickSummary.vue";
import Totals from "@/components/Totals.vue";
import Transactions from "@/components/Transactions.vue";

export default {
  name: "HomePage",
  components: {
    Liquidity,
    QuickSummary,
    Totals,
    Transactions,
  },
};
</script>
