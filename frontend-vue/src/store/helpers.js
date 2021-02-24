import moment from "moment";

export function sumAmountsAfterDate(transactions, initialDate) {
  return transactions.reduce(
    (total, transaction) =>
      moment(transaction.date).isSameOrAfter(initialDate)
        ? total + transaction.amount
        : total,
    0.0,
  );
}

export function sumAmountsInMonth(transactions, monthMoment) {
  return transactions.reduce(
    (total, transaction) =>
      moment(transaction.date, "YYYY-MM-DD").isBetween(
        monthMoment,
        monthMoment,
        "month",
        "[]",
      )
        ? total + transaction.amount
        : total,
    0.0,
  );
}

export function toCurrency(n) {
  let formatter = new Intl.NumberFormat("en-us", {
    style: "currency",
    currency: "USD",
  });
  return formatter.format(n / 100);
}

export function scrollToTop() {
  window.scrollTo(0,0);
}

export function scrollToBottom() {
  window.scrollTo(0,document.body.scrollHeight);
}
