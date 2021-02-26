const colors = require("tailwindcss/colors");

module.exports = {
  purge: ["./src/**/*.html", "./src/**/*.vue"],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      maxWidth: {
        "7xl": "87rem",
        "4xl": "61rem",
        xs: "23rem",
        "1xs": "18rem",
        "2xs": "10rem",
        "3xs": "9rem",
      },
      borderWidth: {
        "3": "3px",
        "5": "5px",
        "6": "6px",
        "7": "7px",
      },
      width: {
        "col-1": "7rem",
        "col-2": "7rem",
        "col-3": "8rem",
        "col-4": "8rem",
        "col-5": "6rem",
        "col-6": "8rem",
        "col-7": "11rem",
        "col-8": "12rem",
      },
      scale: {
        "96": ".96",
        "97": ".97",
        "98": ".98",
        "99": ".99",
      },
      screens: { uncollapsed: "952px" },
    },
    colors: colors,
  },
  variants: {
    extend: {
      zIndex: ["hover"],
      position: ["hover"],
      borderWidth: ["focus"],
    },
  },
  plugins: [],
};
