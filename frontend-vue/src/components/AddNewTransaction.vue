<template>
  <form
    :class="{ hidden: !isShown }"
    class="rounded-xl bg-coolGray-200 shadow-md pr-4 pt-3 pb-2 pl-2 flex flex-col"
  >
    <!-- start of input fields -->
    <div class="lg:flex items-start">
      <!-- start of non-description input fields -->
      <div class="flex flex-wrap lg:w-8/12">
        <!-- Date -->
        <div class="flex py-2 ">
          <label for="date" class="pr-2 pl-3 ">Date:</label>
          <input
            id="date"
            :class="[requiredField, { 'text-warmGray-500': !transaction.date }]"
            class="max-w-2xs pl-1 rounded-md focus:outline-none focus:ring-1 focus:ring-warmGray-500"
            type="date"
            v-model="transaction.date"
          />
        </div>

        <!-- amount -->
        <div class="flex flex-wrap py-2">
          <label for="amount" class="pr-2 pl-3">Amount:</label>
          <div class="flex max-w-3xs ">
            <span class="text-emerald-900">$</span>
            <input
              id="amount"
              :class="requiredField"
              class="w-full pl-2 rounded-md focus:outline-none focus:ring-1 focus:ring-warmGray-500"
              type="number"
              step="0.01"
              placeholder="0.00"
              v-model="transaction.amount"
            />
          </div>
        </div>

        <!-- category -->
        <div class="flex flex-wrap py-2">
          <label for="category" class="pr-2 pl-3">Category:</label>
          <select
            id="category"
            class="pl-1 rounded-md bg-white w-56 border border-warmGray-400 focus:outline-none focus:ring-1 focus:ring-warmGray-500"
            :class="{ 'text-warmGray-500': !transaction.category }"
            v-model="transaction.category"
            @change="transaction.subcategory = null"
          >
            <option class="text-warmGray-500" :value="null">(none)</option>
            <option
              v-for="category in sortByName(categories)"
              :value="category"
              :key="category.id"
              class="text-warmGray-800"
            >
              {{ category.name }}
            </option>
          </select>
        </div>

        <!-- subcategory -->
        <div class="flex flex-wrap py-2">
          <label for="subcategory" class="pr-2 pl-3">Subcategory:</label>
          <select
            id="subcategory"
            class="pl-1 rounded-md bg-white w-52 border border-warmGray-400 focus:outline-none focus:ring-1 focus:ring-warmGray-500"
            :class="{ 'text-warmGray-500': !transaction.subcategory }"
            v-model="transaction.subcategory"
          >
            <option selected class="text-warmGray-500" :value="null"
              >(none)</option
            >
            <template v-if="transaction.category">
              <option
                v-for="subcategory in sortByName(
                  transaction.category.subcategories,
                )"
                :value="subcategory"
                :key="subcategory.id"
                class="text-warmGray-800"
              >
                {{ subcategory.name }}
              </option>
            </template>
          </select>
        </div>

        <!-- Method -->
        <div class="flex flex-wrap py-2">
          <label for="method" class="pr-2 pl-3">Method:</label>
          <select
            id="method"
            :class="[
              requiredField,
              { 'text-warmGray-500': !transaction.method },
            ]"
            class="pl-1 rounded-md bg-white w-52 focus:outline-none focus:ring-1 focus:ring-warmGray-500"
            v-model="transaction.method"
          >
            <option disabled class="text-warmGray-500" :value="null"
              >Select a method...</option
            >
            <option
              v-for="method in sortByName(methods)"
              :value="method"
              :key="method.id"
              class="text-warmGray-800"
            >
              {{ method.name }}
            </option>
          </select>
        </div>

        <!-- To Account -->
        <div class="flex flex-wrap py-2">
          <label for="toAccount" class="pr-2 pl-3">To:</label>
          <select
            id="toAccount"
            class="pl-1 rounded-md bg-white w-56 focus:outline-none focus:ring-1 focus:ring-warmGray-500"
            :class="[
              requiredField,
              { 'text-warmGray-500': !transaction.toAccount },
            ]"
            v-model="transaction.toAccount"
          >
            <option class="text-warmGray-500" :value="null">(none)</option>
            <option
              v-for="account in sortByName(accounts)"
              :value="account"
              :key="account.id"
              class="text-warmGray-800"
            >
              {{ account.name }}
            </option>
          </select>
        </div>

        <!-- From Account -->
        <div class="flex flex-wrap py-2">
          <label for="fromAccount" class="pr-2 pl-3">From:</label>
          <select
            id="toAccount"
            class="pl-1 rounded-md bg-white w-56 focus:outline-none focus:ring-1 focus:ring-warmGray-500"
            :class="[
              requiredField,
              { 'text-warmGray-500': !transaction.fromAccount },
            ]"
            v-model="transaction.fromAccount"
          >
            <option class="text-warmGray-500" :value="null">(none)</option>
            <option
              v-for="account in sortByName(accounts)"
              :value="account"
              :key="account.id"
              class="text-warmGray-800"
            >
              {{ account.name }}
            </option>
          </select>
        </div>

        <!-- tip -->
        <div class="flex flex-wrap py-2">
          <label for="tip" class="pr-2 pl-3">Tip:</label>
          <div class="flex max-w-3xs ">
            <span class="text-emerald-900">$</span>
            <input
              id="tip"
              class="w-full pl-2 rounded-md border border-warmGray-400 focus:outline-none focus:ring-1 focus:ring-warmGray-500"
              type="number"
              step="0.01"
              placeholder="0.00"
              v-model="transaction.tip"
            />
          </div>
        </div>

        <!-- recurring -->
        <div class="flex items-center py-2">
          <input
            id="recurring"
            class="ml-3 cursor-pointer focus:outline-none focus:ring-1 focus:ring-warmGray-500"
            type="checkbox"
            v-model="transaction.isRecurring"
          />
          <label for="recurring" class="pr-2 pl-1 cursor-pointer"
            >Recurring?</label
          >
        </div>
      </div>
      <!-- end of non-description input fields -->

      <!-- description -->
      <div class="flex flex-col py-2 lg:w-4/12 pl-3 ">
        <label for="description" class="pr-2">Description:</label>
        <textarea
          id="recurring"
          class="px-2 py-1 cursor-pointer w-full h-20 rounded-xl border border-warmGray-400 focus:outline-none focus:ring-1 focus:ring-warmGray-500"
          placeholder="Describe the transaction..."
          v-model="transaction.description"
        ></textarea>
      </div>
      <!-- end of description -->
    </div>

    <!-- buttons -->
    <div class="flex justify-center lg:justify-end">
      <CancelButton
        @buttonClicked="cancel()"
        title="Cancel"
        class="transform "
        :msg="''"
      />
      <AddButton
        @buttonClicked="save()"
        title="Save Transaction"
        class="transform "
        :msg="''"
      />
    </div>
  </form>
</template>

<script>
import AddButton from "@/components/AddButton.vue";
import CancelButton from "@/components/CancelButton.vue";
import moment from "moment";
import { mapState, mapActions } from "vuex";

export default {
  name: "AddNewTransaction",
  components: {
    AddButton,
    CancelButton,
  },
  props: {
    isShown: {
      type: Boolean,
      required: true,
    },
  },
  emits: ["close"],
  data() {
    return {
      transaction: this.getEmptyTransaction(),
      submitted: false,
    };
  },
  computed: {
    ...mapState(["categories", "types", "accounts", "methods"]),
    requiredField() {
      return {
        "border-red-600":
          this.submitted && !this.isValid(this.computedTransaction),
        "border-2": this.submitted,
        "border-warmGray-400": !this.submitted,
        border: !this.submitted,
        "focus:border-red-900": this.submitted,
        "focus:outline-none": this.submitted,
        "border-green-600":
          this.submitted && this.isValid(this.computedTransaction),
      };
    },
    computedTransaction() {
      let type = null;
      let fromAccount = this.transaction.fromAccount;
      let toAccount = this.transaction.toAccount;
      if (toAccount && fromAccount) {
        type = this.getType("Fund Transfer");
      } else if (toAccount) {
        type = this.getType("Income");
      } else if (fromAccount) {
        type = this.getType("Expense");
      }

      return {
        type,
        date: this.transaction.date
          ? moment(this.transaction.date).format("YYYY-MM-DD")
          : null,
        amount: this.transaction.amount
          ? Math.abs(parseInt(this.transaction.amount * 100))
          : null,
        method: this.transaction.method,
        category: this.transaction.category,
        subcategory: this.transaction.subcategory,
        fromAccount,
        toAccount,
        description: this.transaction.description,
        tip: this.transaction.tip
          ? Math.abs(parseInt(this.transaction.tip))
          : 0.0,
        isRecurring: this.transaction.isRecurring,
      };
    },
  },
  methods: {
    ...mapActions(["addTransaction"]),
    getEmptyTransaction() {
      return {
        date: null,
        amount: null,
        category: null,
        subcategory: null,
        toAccount: null,
        fromAccount: null,
        method: null,
        tip: null,
        isRecurring: false,
        description: "",
      };
    },
    sortByName(arr) {
      let sortMe = [...arr];
      return sortMe.sort(function(a, b) {
        let nameA = a.name.toUpperCase(); // ignore upper and lowercase
        let nameB = b.name.toUpperCase(); // ignore upper and lowercase
        if (nameA < nameB) return -1;
        if (nameA > nameB) return 1;
        return 0;
      });
    },
    getType(str) {
      return this.types.filter(type => type.name === str)[0];
    },
    isValid(t) {
      let arr = [t.amount, t.date, t.type, t.method];
      if (arr.some(el => el === null)) return false;
      return true;
    },
    resetTransaction() {
      this.submitted = false;
      this.transaction = this.getEmptyTransaction();
    },
    cancel() {
      this.resetTransaction();
      this.$emit("close", false);
    },
    save() {
      this.submitted = true;
      if (!this.isValid(this.computedTransaction)) return;

      this.addTransaction(this.computedTransaction);
      this.resetTransaction();
      this.$emit("close", true);
    },
  },
};
</script>
