<template>
  <div
    @mousedown.prevent="toggleAddButtonActive(true)"
    @mouseup="toggleAddButtonActive(false)"
    @mouseleave="toggleAddButtonActive(false)"
    @click="$emit('buttonClicked')"
    class="flex max-w-max items-center cursor-pointer hover:underline text-warmGray-700 text-lg font-medium"
  >
    <PlusIcon
      :classes="['w-10', 'h-10', 'text-green-600', 'fill-current','hover:text-green-700']"
      :class="[{ 'scale-90': addButtonActive }, 'transform']"
    />
    <span
      class="pl-3 select-none"
      :class="[{ 'text-warmGray-500': addButtonActive }]"
      >{{this.msg}}</span
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
      default: "Add"
    }
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
