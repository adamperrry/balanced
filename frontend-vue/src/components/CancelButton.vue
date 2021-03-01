<template>
  <div
    @mousedown.prevent="toggleAddButtonActive(true)"
    @mouseup="toggleAddButtonActive(false)"
    @mouseleave="toggleAddButtonActive(false)"
    @click="$emit('buttonClicked')"
    class="flex items-center hover:underline text-warmGray-700 text-lg font-medium"
  >
    <PlusIcon
      :classes="['w-10', 'h-10', 'text-red-500', 'fill-current','hover:text-red-600']"
      :class="[{ 'scale-90': addButtonActive }, 'transform']"
      class="transform rotate-45 cursor-pointer"
    />
    <span
      class="pl-3 select-none"
      :class="[{ 'text-warmGray-500': addButtonActive }]"
      >{{ this.msg }}</span
    >
  </div>
</template>

<script>
import PlusIcon from "@/assets/PlusIcon.vue";
export default {
  name: "AddButton",
  components: {
    PlusIcon,
  },
  emits: ["buttonClicked"],
  props: {
    msg: {
      type: String,
      default: "Cancel",
    },
  },
  data() {
    return {
      addButtonActive: false,
    };
  },
  methods: {
    toggleAddButtonActive(state) {
      this.addButtonActive = state;
    },
  },
};
</script>
