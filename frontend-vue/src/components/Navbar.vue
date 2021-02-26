<template>
  <div class="shadow-sm font-sans bg-emerald-50 flex flex-wrap justify-center">
    <nav class="max-w-7xl flex-grow pt-2 bg-emerald-200" id="nav">
      <!-- container for logo and selectable, needed for collapsability -->
      <div
        class="flex flex-col uncollapsed:flex-row uncollapsed:justify-items-between uncollapsed:items-end"
      >
        <!-- logo and button container -->
        <div class="flex justify-between">
          <!-- "logo" -->
          <div
            class="select-none relative top-2 right-1 text-7xl font-bold text-emerald-900 tracking-tighter"
          >
            balanced.
          </div>

          <!-- Add button to expand here? -->
          <MenuIcon
            @click="toggleCollapse()"
            class="uncollapsed:hidden cursor-pointer self-end mr-3 mb-1 text-emerald-600"
            :classes="[
              'w-10',
              'h-10',
              'hover:text-emerald-800',
              'fill-current',
            ]"
          />
        </div>

        <!-- start of page links -->
        <div
          class="flex-col items-end uncollapsed:flex uncollapsed:flex-row uncollapsed:flex-grow justify-end text-lg uncollapsed:py-0 py-2"
          :class="{ hidden: collapsed, flex: !collapsed }"
        >
          <router-link
            v-for="page in pages"
            :key="page.id"
            :to="page.path"
            class=" text-emerald-800 px-3 mr-2 uncollapsed:mr-0 uncollapsed:px-5 bg-emerald-200 pb-1 pt-2 uncollapsed:pt-3 font-medium uncollapsed:border-t-4 uncollapsed:border-r-0 border-r-7 border-emerald-200 hover:border-white"
          >
            {{ page.name }}
          </router-link>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import MenuIcon from "@/assets/MenuIcon.vue";

export default {
  name: "Navbar",
  components: {
    MenuIcon,
  },
  methods: {
    toggleCollapse() {
      this.collapsed = !this.collapsed;
    },
  },
  data() {
    return {
      pages: [
        { id: 1, name: "Home", path: "/" },
        { id: 2, name: "Transaction Log", path: "/transactions" },
        { id: 3, name: "Accounts", path: "/accounts" },
        { id: 4, name: "Categories", path: "/categories" },
        { id: 5, name: "Summaries", path: "/summaries" },
      ],
      collapsed: true,
    };
  },
};
</script>

<style scoped>
#nav a.router-link-exact-active {
  border-color: rgba(255, 255, 255, 1);
  color: rgba(38, 38, 38, 1);
  font-weight: 600;
}
</style>
