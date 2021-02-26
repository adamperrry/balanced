<template>
  <svg
    id="Capa_1"
    xmlns="http://www.w3.org/2000/svg"
    viewBox="0 0 60.123 60.123"
    :class="classes"
  >
    <g>
      <path
        d="M57.124,51.893H16.92c-1.657,0-3-1.343-3-3s1.343-3,3-3h40.203c1.657,0,3,1.343,3,3S58.781,51.893,57.124,51.893z"
      />
      <path
        d="M57.124,33.062H16.92c-1.657,0-3-1.343-3-3s1.343-3,3-3h40.203c1.657,0,3,1.343,3,3
		C60.124,31.719,58.781,33.062,57.124,33.062z"
      />
      <path
        d="M57.124,14.231H16.92c-1.657,0-3-1.343-3-3s1.343-3,3-3h40.203c1.657,0,3,1.343,3,3S58.781,14.231,57.124,14.231z"
      />
      <circle cx="4.029" cy="11.463" r="4.029" />
      <circle cx="4.029" cy="30.062" r="4.029" />
      <circle cx="4.029" cy="48.661" r="4.029" />
    </g>
  </svg>
</template>

<script>
export default {
  name: "MenuIcon",
  // classes must be an array of strings, i.e. ['w-4','h-4','text-emerald-800','hover:text-emerald-900','fill-current']
  props: ["classes"],
};
</script>

<style scoped>
.shadow-filter {
  -webkit-filter: drop-shadow(0px 2px 2px rgba(0, 0, 0, 0.15));
  filter: drop-shadow(0px 2px 2px rgba(0, 0, 0, 0.15));
  /* Similar syntax to box-shadow */
}
</style>
