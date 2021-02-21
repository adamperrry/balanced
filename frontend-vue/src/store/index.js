import axios from "axios";
import moment from "moment";
import { createStore } from "vuex";

const baseUrl = "http://localhost:8080/api/";

const getInitialState = () => {
  return {
    transactions: [],
    transactionSettings: [],
    accounts: [],
    categories: [],
    subcategories: [],
    types: [],
    methods: [],
  };
};

const sumAmountsAfterDate = (transactions, initialDate) => {
  return transactions.reduce(
    (total, transaction) =>
      moment(transaction.date).isSameOrAfter(initialDate)
        ? total + transaction.amount
        : total,
    0.0
  );
};

//to handle state
const state = getInitialState();

//to handle state
const getters = {
  accountBalances(state, getters) {
    let accountBalances = [];
    for (let account of state.accounts) {
      let initialDate = moment(account.initialBalanceDate, "YYYY-MM-DD");

      let income = sumAmountsAfterDate(
        getters.transactionsToAccountId(account.id),
        initialDate
      );

      let expenses = sumAmountsAfterDate(
        getters.transactionsFromAccountId(account.id),
        initialDate
      );

      let balance = account.initialBalance + income - expenses;

      accountBalances.push({
        id: account.id,
        balance: balance,
        isLiquid: account.isLiquid,
        name: account.name,
      });
    }

    return accountBalances;
  },
  transactionsToAccountId: (state) => (id) => {
    return state.transactions.filter(
      (transaction) => transaction.toAccount && transaction.toAccount.id === id
    );
  },
  transactionsFromAccountId: (state) => (id) => {
    return state.transactions.filter(
      (transaction) =>
        transaction.fromAccount && transaction.fromAccount.id === id
    );
  },
  expenses(state) {
    return state.transactions.filter(
      (transaction) => transaction.type.name === "Expense"
    );
  },
  income(state) {
    return state.transactions.filter(
      (transaction) => transaction.type.name === "Income"
    );
  },
};

//to handle actions
const actions = {
  getData({ commit }) {
    let stateObject = getInitialState();
    let keys = Object.keys(stateObject);
    keys.forEach((key) => {
      axios.get(baseUrl + key).then((response) => {
        stateObject[key] = response.data;
        commit("SET_DATA", stateObject);
      });
    });
  },
};

//to handle mutations
const mutations = {
  SET_DATA(state, data) {
    Object.assign(state, data);
  },
};

//export store module
export default createStore({
  state,
  getters,
  actions,
  mutations,
});
