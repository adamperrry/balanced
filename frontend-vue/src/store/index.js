import axios from "axios";
import moment from "moment";
import { createStore } from "vuex";
import { sumAmountsAfterDate } from "@/store/helpers";

const baseUrl = "http://localhost:8080/api/";

const getInitialState = () => {
  // should only be the things we're getting from API, if not change the getData action.
  // Each key name corresponds to an API path, and this must be true for the data to mount correctly
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
        initialDate,
      );

      let expenses = sumAmountsAfterDate(
        getters.transactionsFromAccountId(account.id),
        initialDate,
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
  transactionsToAccountId: state => id => {
    return state.transactions.filter(
      transaction => transaction.toAccount && transaction.toAccount.id === id,
    );
  },
  transactionsFromAccountId: state => id => {
    return state.transactions.filter(
      transaction =>
        transaction.fromAccount && transaction.fromAccount.id === id,
    );
  },
  expenses(state) {
    return state.transactions.filter(
      transaction => transaction.type.name === "Expense",
    );
  },
  income(state) {
    return state.transactions.filter(
      transaction => transaction.type.name === "Income",
    );
  },
};

//to handle actions
const actions = {
  getData({ commit }) {
    let stateObject = getInitialState();
    let keys = Object.keys(stateObject);
    keys.forEach(key => {
      axios.get(baseUrl + key).then(response => {
        stateObject[key] = response.data;
        commit("SET_DATA", stateObject);
      });
    });
  },
  addTransaction({ commit }, transaction) {
    axios.post(baseUrl + "transactions/", transaction).then(
      response => {
        console.log("Successfully added to DB: ", response.data);
        commit("ADD_TRANSACTION", response.data);
      },
      error => {
        console.log("Couldn't save to DB: ", error);
      },
    );
  },
  deleteTransaction({ commit }, id) {
    axios.delete(baseUrl + "transactions/" + id).then(
      response => {
        console.log("Successfully deleted from DB: ", response.data);
        commit("DELETE_TRANSACTION", id);
      },
      error => {
        console.log("Couldn't delete from DB: ", error);
      },
    );
  },
  editTransaction({ commit }, transaction) {
    axios.put(baseUrl + "transactions/" + transaction.id ,transaction).then(
      response => {
        console.log("Successfully updated in DB: ", response.data);
        commit("UPDATE_TRANSACTION", transaction);
      },
      error => {
        console.log("Couldn't update in DB: ", error);
      },
    );
  },
};

//to handle mutations
const mutations = {
  SET_DATA(state, data) {
    Object.assign(state, data);
  },
  ADD_TRANSACTION(state, transaction) {
    state.transactions.push(transaction);
  },
  DELETE_TRANSACTION(state, id) {
    let deleteIndex = state.transactions
      .map(transaction => transaction.id)
      .indexOf(id);
    state.transactions.splice(deleteIndex, 1);
  },
  UPDATE_TRANSACTION(state, transaction) {
    let updateIndex = state.transactions
      .map(transaction => transaction.id)
      .indexOf(transaction.id);
    state.transactions[updateIndex] = transaction;
  },
};

//export store module
export default createStore({
  state,
  getters,
  actions,
  mutations,
});
