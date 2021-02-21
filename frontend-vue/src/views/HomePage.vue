<template>
  <div>
    <h1>Test Home Page</h1>
    <div>
      <Liquidity />
      <QuickSummary />
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Liquidity from "@/components/Liquidity.vue";
import QuickSummary from "@/components/QuickSummary.vue";
import { mapState } from "vuex";

export default {
  name: "HomePage",
  components: {
    Liquidity,
    QuickSummary,
  },
  computed: {
    ...mapState(["transactions"]),
  },
};
</script>
