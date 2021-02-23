<template>
  <div class="flex justify-center pt-10 ">
    <div class="flex flex-col flex-grow  max-w-7xl ">
      <div class="flex">
        <div class="flex flex-col flex-grow justify-between">
          <Liquidity />
          <Totals />
          <!-- other component placeholder -->
        </div>
        <div class="flex flex-col flex-grow max-w-max">
          <QuickSummary />
        </div>
      </div>
      <!-- placeholder for transactions table -->
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Liquidity from "@/components/Liquidity.vue";
import QuickSummary from "@/components/QuickSummary.vue";
import Totals from "@/components/Totals.vue";
import { mapState } from "vuex";

export default {
  name: "HomePage",
  components: {
    Liquidity,
    QuickSummary,
    Totals,
  },
  computed: {
    ...mapState(["transactions"]),
  },
};
</script>
