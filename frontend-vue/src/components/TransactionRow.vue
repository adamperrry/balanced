<template>
  <div
    class="relative overflow-hidden transition-shadow flex border-b cursor-pointer hover:z-40 border-gray-500 hover:shadow-md text-warmGray-800"
    :class="[
      {
        'bg-red-100': isExpense(transaction),
        'bg-white': isTransfer(transaction),
        'bg-emerald-50': isIncome(transaction),
      },
    ]"
    @mouseleave="hideOptions()"
  >
    <div @click="toggleOptions()" class="py-1 px-3 flex flex-grow">
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

    <!-- start of edit/delete drawer -->
    <div
      class="flex absolute left-0 h-full transform transition-transform w-col-1"
      :class="{
        '-translate-x-full': !optionsShown,
        'translate-x-0': optionsShown,
      }"
    >
      <div
        class="flex-auto flex items-center justify-center bg-red-500 hover:bg-red-600"
        title="Delete"
        @click="deleteTransaction(transaction.id)"
      >
        <TrashIcon
          class=""
          :classes="['w-5', 'h-5', 'text-white', 'fill-current']"
        />
      </div>
      <div
        class="flex-auto flex items-center justify-center hover:bg-white bg-coolGray-100 border-r border-gray-500"
        title="Edit"
        @click="this.$emit('edit', transaction)"
      >
        <EditIcon
          :classes="['w-5', 'h-5', 'text-warmGray-600', 'fill-current']"
        />
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import moment from "moment";
import EditIcon from "@/assets/EditIcon.vue";
import TrashIcon from "@/assets/TrashIcon.vue";
import { mapActions } from "vuex";

export default {
  name: "TransactionRow",
  components: {
    EditIcon,
    TrashIcon,
  },
  emits: ["edit"],
  props: {
    transaction: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      optionsShown: false,
    };
  },
  computed: {},
  methods: {
    ...mapActions(["deleteTransaction"]),
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
    toggleOptions() {
      this.optionsShown = !this.optionsShown;
    },
    hideOptions() {
      this.optionsShown = false;
    },
  },
};
</script>
