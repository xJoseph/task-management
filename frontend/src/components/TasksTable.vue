<template>
  <div class="tasks_container">
    <h1>Lista de tarefas</h1>

    <div class="add_task_container">
      <button class="add_task_button" v-on:click="isCreatingTask = true">
        Nova Task
      </button>
    </div>

    <CreateTaskModal
      v-if="isCreatingTask"
      :isCreatingTask="isCreatingTask"
      @update:isCreatingTask="updateIsCreatingTask"
    />

    <table class="tasks_table">
      <TasksTableHeader />

      <tbody>
        <tr v-for="task in tasks" v-bind:key="task.id">
          <td class="status_icon" v-on:click="completeTask(task.id)">
            {{ task.status ? "✅" : "❌" }}
          </td>

          <td v-if="isEditing === task.id"><input v-model="task.title" /></td>
          <td v-else>{{ task.title }}</td>

          <td v-if="isEditing === task.id">
            <input v-model="task.description" />
          </td>
          <td v-else>{{ task.description }}</td>

          <td>{{ task.creationDate.split("T")[0] }}</td>

          <td v-if="isEditing === task.id">
            <input type="date" v-model="task.dueDate" />
          </td>
          <td v-else>{{ task.dueDate.split("T")[0] }}</td>

          <td>
            {{
              new Date(task.dueDate) < new Date()
                ? "Atrasada"
                : "Dentro do prazo"
            }}
          </td>

          <td class="edit_icon">
            <Pencil
              v-on:click="editTask(task)"
              :size="20"
              v-if="isEditing !== task.id"
            />
            <button v-on:click="updateTask(task)" v-if="isEditing === task.id">
              Salvar
            </button>
          </td>

          <td class="delete_icon">
            <X v-on:click="deleteTask(task.id)" :size="20" />
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script lang="ts">
import { Pencil, X } from "lucide-vue-next";
import { defineComponent } from "vue";

import TaskModel from "../models/TaskModel";
import TasksService from "../services/TasksService";
import CreateTaskModal from "./CreateTaskModal.vue";
import TasksTableHeader from "./TasksTableHeader.vue";

interface TasksTableData {
  tasks: TaskModel[];
  isCreatingTask: boolean;
  isEditing: number | null;
}

export default defineComponent({
  name: "TasksTable",
  components: {
    X,
    Pencil,
    CreateTaskModal,
    TasksTableHeader,
  },
  data(): TasksTableData {
    return {
      tasks: [],
      isCreatingTask: false,
      isEditing: null,
    };
  },
  methods: {
    getTasks(): void {
      TasksService.getTasks().then((response: { data: TaskModel[] }) => {
        this.tasks = response.data;
      });
    },

    updateIsCreatingTask(newValue: boolean): void {
      this.getTasks();
      this.isCreatingTask = newValue;
    },

    editTask(task: TaskModel): void {
      this.isEditing = task.id;
    },

    updateTask(task: TaskModel): void {
      TasksService.updateTask(task).then(() => {
        this.getTasks();
        this.isEditing = null;
      });
    },

    deleteTask(id: number): void {
      TasksService.deleteTask(id).then(() => {
        this.getTasks();
      });
    },

    completeTask(id: number): void {
      TasksService.completeTask(id).then(() => {
        this.getTasks();
      });
    },
  },
  created() {
    this.getTasks();
  },
});
</script>

<style>
.tasks_container {
  width: 100%;
}
.add_task_container {
  display: flex;
  justify-content: flex-end;
  flex-direction: row;
  margin-bottom: 20px;
}
.add_task_button {
  cursor: pointer;
  background: #6ecf65;
  border: 0px;
  width: 120px;
  height: 30px;
  transition: all 0.3s ease;
}
.add_task_button:hover {
  background: #42b983;
  color: white;
}
.tasks_table {
  width: 100%;
  border-collapse: collapse;
  border: 1px;
}
.tasks_table > tbody > tr > td {
  border: 1px solid black;
}
.status_icon {
  cursor: pointer;
}
.status_icon:hover {
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}
.edit_icon {
  cursor: pointer;
  color: #ffc400;
  transition: transform 0.3s ease;
}
.edit_icon:hover {
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}
.delete_icon {
  cursor: pointer;
  color: #ff0000;
  transition: transform 0.3s ease;
}
.delete_icon:hover {
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}
</style>
