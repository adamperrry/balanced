const colors = require("tailwindcss/colors");

module.exports = {
  purge: ["./src/**/*.html", "./src/**/*.vue"],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      maxWidth: {
        "7xl": "82rem",
        "4xl": "57rem",
        xs: "22rem",
      },
    },
    colors: colors,
  },
  variants: {
    extend: {
      // borderWidth: ["hover", "focus"],
    },
  },
  plugins: [],
};
