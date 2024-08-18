<template>
  <div id="app">
    <img class="logo" alt="Vue logo" src="@/assets/logo.png" />
    <TasksTable />
  </div>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";
import TasksTable from "./components/TasksTable.vue";

@Options({
  components: {
    TasksTable,
  },
})
export default class App extends Vue {}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.logo {
  width: 100px;
  height: 100px;
}
</style>
