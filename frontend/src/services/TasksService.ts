import TaskModel from "@/models/TaskModel";
import axios from "axios";

const TASKS_URL = "http://localhost:3000/api/tasks";

class TasksService {
  getTasks() {
    return axios.get(TASKS_URL);
  }

  getTask(id: number) {
    return axios.get(`${TASKS_URL}/${id}`);
  }

  createTask(task: { title: string; description: string; dueDate: string }) {
    return axios.post(TASKS_URL, task);
  }

  updateTask(task: TaskModel) {
    return axios.put(`${TASKS_URL}/${task.id}`, task);
  }

  completeTask(id: number) {
    return axios.put(`${TASKS_URL}/${id}/complete`);
  }

  deleteTask(id: number) {
    return axios.delete(`${TASKS_URL}/${id}`);
  }
}

export default new TasksService();
