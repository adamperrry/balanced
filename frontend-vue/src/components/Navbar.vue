<template>
  <div class="shadow-sm font-sans bg-emerald-50 flex flex-wrap justify-center">
    <nav
      class="max-w-7xl flex items-end justify-between flex-wrap 
    flex-grow  pt-2 bg-emerald-200"
      id="nav"
    >
      <div
        class="select-none relative top-2 right-1 text-7xl font-bold text-emerald-900 tracking-tighter"
      >
        balanced.
      </div>
      <div class="flex flex-grow flex-wrap text-lg justify-end">
        <router-link
          v-for="page in pages"
          :key="page.id"
          :to="page.path"
          class=" text-emerald-800 px-5 bg-emerald-200 pb-1 pt-3 font-medium border-t-4 border-emerald-200 hover:border-white"
        >
          {{ page.name }}
        </router-link>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: "Navbar",
  data() {
    return {
      pages: [
        { id: 1, name: "Home", path: "/" },
        { id: 2, name: "Transaction Log", path: "/transactions" },
        { id: 3, name: "Accounts", path: "/accounts" },
        { id: 4, name: "Categories", path: "/categories" },
        { id: 5, name: "Summaries", path: "/summaries" },
      ],
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
