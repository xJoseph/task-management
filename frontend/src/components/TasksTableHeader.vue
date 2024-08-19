<template>
  <thead>
    <tr>
      <td>Status</td>
      <td>Título</td>
      <td>Descrição</td>
      <td>Data de criação</td>
      <td>Data de expiração</td>
      <td>Situação</td>
    </tr>
  </thead>
</template>

<script lang="ts">
  import { defineComponent } from "vue";
  
export default defineComponent({
  name: "TasksTableHeader",
});
</script>

<style scoped>
thead > tr > td {
  background-color: #f2f2f2;
  border: 1px solid black;
  font-weight: bold;
  text-align: center;
  padding: 10px;
}
</style>
