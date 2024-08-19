<template>
  <div class="container">
    <div class="modal">
      <div class="form">
        <div class="form_header">
          <h1 style="width: 80%">Criar Tarefa</h1>
          <X class="close_modal" :size="40" v-on:click="closeModal" />
        </div>

        <div class="form_group">
          <label for="title">Título:</label>
          <input type="text" id="title" name="title" v-model="title" />
        </div>

        <div class="form_group">
          <label for="description">Descrição:</label>
          <input
            type="text"
            id="description"
            name="description"
            v-model="description"
          />
        </div>

        <div class="form_group">
          <label for="dueDate">Data de vencimento:</label>
          <input type="date" id="dueDate" name="dueDate" v-model="dueDate" />
        </div>

        <button class="form_button" v-on:click="createTask">
          Criar Tarefa
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { X } from "lucide-vue-next";
import { defineComponent } from "vue";
  
import TasksService from "../services/TasksService";

export default defineComponent({
  name: "CreateTaskModal",
  components: {
    X,
  },
  props: {
    isCreatingTask: Boolean,
  },
  data() {
    return {
      title: "",
      description: "",
      dueDate: "",
    };
  },

  methods: {
    createTask(): void {
      TasksService.createTask({
        title: this.title,
        description: this.description,
        dueDate: this.dueDate,
      });

      this.closeModal();
      this.title = "";
      this.description = "";
      this.dueDate = "";
    },

    closeModal() {
      this.$emit("update:isCreatingTask", false);
    },
  },
});
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  z-index: 1;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
}
.modal {
  display: flex;
  padding: 20px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  width: 50%;
}
.form {
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  gap: 35px;
  width: 100%;
}
.form_header {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: row;
  width: 100%;
}
.close_modal {
  width: 10%;
  cursor: pointer;
}
.form_group {
  display: flex;
  flex-direction: row;
  width: 75%;
}
.form_button {
  cursor: pointer;
  background: #6ecf65;
  border: 0px;
  width: 50%;
  height: 30px;
  transition: all 0.3s ease;
  font-weight: bold;
}
.form_button:hover {
  background: #42b983;
  color: white;
}
</style>
