<template>
  <div class="flex justify-center">
    <div
      class="flex flex-col flex-grow  max-w-7xl mx-3 divide-y-5 divide-dotted"
    >
      <!-- placeholder for transactions table -->
      <Transactions :showAll="true"/>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Transactions from "@/components/Transactions.vue";

export default {
  name: "TransactionsPage",
  components: {
    Transactions,
  },
};
</script>
