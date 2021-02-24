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
        xxs: "18rem",
      },
      borderWidth: {
        '3' : '3px',
        '5' : '5px',
        '6' : '6px',
        '7' : '7px'
      },
      width: {
        'col-1' : '7rem',
        'col-2' : '8rem',
        'col-3' : '9rem',
        'col-4' : '9rem',
        'col-5' : '8rem',
        'col-6' : '8rem',
        'col-7' : '8rem',
        'col-8' : '12rem',
      },
      scale: {
        '96' : '.96',
        '97' : '.97',
        '98' : '.98',
        '99' : '.99',
      }
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
