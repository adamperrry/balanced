<template>
  <svg
    id="Capa_1"
    xmlns="http://www.w3.org/2000/svg"
    viewBox="0 0 52 52"
    :class="classes"
  >
    <path
      d="M26,0C11.664,0,0,11.663,0,26s11.664,26,26,26s26-11.663,26-26S40.336,0,26,0z M38.5,28H28v11c0,1.104-0.896,2-2,2
	s-2-0.896-2-2V28H13.5c-1.104,0-2-0.896-2-2s0.896-2,2-2H24V14c0-1.104,0.896-2,2-2s2,0.896,2,2v10h10.5c1.104,0,2,0.896,2,2
	S39.604,28,38.5,28z"
    />
  </svg>
</template>

<script>
export default {
  name: "PlusIcon",
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
