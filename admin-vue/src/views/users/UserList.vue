<template>
  <el-breadcrumb name="面包屑">
    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>用户管理</el-breadcrumb-item>
    <el-breadcrumb-item>用户列表</el-breadcrumb-item>
  </el-breadcrumb>
  <br>
  <el-card class="box-card">
    <el-row :gutter="20">
      <el-col :span="10">
        <el-input
            clearable
            @clear="getUsers"
            v-model="queryInfo.query"
            placeholder="请输入内容"
            class="input-with-select"
        >
          <template #append>
            <el-button :icon="Search" @click="getUsers"/>
          </template>
        </el-input>
      </el-col>
      <el-col :span="6">
        <el-button type="primary" @click="centerDialogVisible = true">添加用户</el-button>
      </el-col>
    </el-row>
    <br>
    <el-table stripe :data="tableData" border style="width: 100%">
      <el-table-column type="index"/>
      <el-table-column prop="username" label="用户名" width="180"/>
      <el-table-column prop="email" label="邮箱" width="180"/>
      <el-table-column prop="mobile" label="电话"/>
      <el-table-column prop="role_name" label="角色"/>
      <el-table-column prop="mg_state" label="状态" v-slot:default="scope">
          <el-switch
              v-model=scope.row.mg_state
              class="mt-2"
              style="margin-left: 24px"
              inline-prompt
              @change="change(scope.row)"
          />
      </el-table-column>
      <el-table-column prop="操作" label="操作" v-slot:default="scope">
        <el-button type="primary" :icon="Edit" circle @click="editUser(scope.row);editDialogVisible = true;"/>
        <el-popconfirm
            confirm-button-text="Yes"
            cancel-button-text="No"
            :icon="InfoFilled"
            icon-color="#626AEF"
            title="确定要删除？"
            @confirm="deleteUser(scope.row)"
        >
          <template #reference>
            <el-button type="danger" :icon="Delete" circle/>
          </template>
        </el-popconfirm>
      </el-table-column>
    </el-table>
    <br>
    <el-pagination
        v-model:current-page="queryInfo.pagenum"
        v-model:page-size="queryInfo.pagesize"
        :page-sizes="[4, 6, 8, 10]"
        layout="total, sizes, prev, pager, next, jumper"
        page-size="10"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </el-card>
  <br>
  <!-- 添加用户对话框 -->
  <el-dialog
      v-model=centerDialogVisible
      title="添加用户"
      width="50%"
      align-center
  >
    <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        class="demo-ruleForm"
        status-icon
        label-width="auto"
    >
      <el-form-item label="用户" prop="username">
        <el-input v-model="ruleForm.username"/>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="ruleForm.password"/>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="ruleForm.email"/>
      </el-form-item>
      <el-form-item label="手机" prop="mobile">
        <el-input v-model="ruleForm.mobile"/>
      </el-form-item>

    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="ruleFormRef.resetFields()">清除</el-button>
        <el-button type="primary" @click="addUsers()">确认</el-button>
      </span>
    </template>
  </el-dialog>

  <!--编辑用户对话框 -->
  <el-dialog
      v-model=editDialogVisible
      title="编辑用户"
      width="50%"
      align-center
  >
    <el-form
        ref="editFormRef"
        :model="editForm"
        :rules="rules"
        class="demo-ruleForm"
        status-icon
        label-width="auto"
    >
      <el-form-item label="用户" prop="username">
        <el-input v-model="editForm.username" disabled/>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="editForm.email"/>
      </el-form-item>
      <el-form-item label="手机" prop="mobile">
        <el-input v-model="editForm.mobile"/>
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="editForm.resetFields()">清除</el-button>
        <el-button type="primary" @click="editUserSubmit(); editDialogVisible = false">确认</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script setup>
import {Delete, Edit, Search, InfoFilled } from '@element-plus/icons-vue'
import {ref} from "vue";
import {ElMessage} from "element-plus";
import {getUsers} from "@/api/user.js";

const centerDialogVisible = ref(false)
const editDialogVisible = ref(false)
const ruleFormRef = ref()
const editFormRef = ref()
const ruleForm = reactive({
  username: "",
  password: "",
  email: "",
  mobile: "",
})

const rules = reactive({
  username: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
    {min: 3, max: 10, message: '长度为3-10', trigger: 'blur'},
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min: 3, max: 10, message: '长度为3-10', trigger: 'blur'},
  ],
  email: [
    {
      type: 'email',
      message: '请输入邮箱',
      trigger: ['blur', 'change'],
    },
  ],
  mobile: [{},],
})

async function addUsers() {
  const res = await api.user.addUser(ruleForm)
  if (res.data.meta.status !== 201) {
    return ElMessage.error("添加用户失败！")
  }
  return ElMessage.success("添加用户成功！")
}


</script>
<script>
import api from "@/api/index.js";
import {ElMessage} from "element-plus";
import {reactive, ref} from "vue";
import {getUsers} from "@/api/user.js";

export default {
  name: "UserList",

  data() {
    return {
      queryInfo: {
        query: "",
        pagenum: 1,
        pagesize: 4,
      },
      total: 0,
      tableData: [
        {
          create_time: 1486720211,
          email: "aaaa",
          id: 500,
          mg_state: true,
          mobile: "vvvvvvvv",
          role_name: "超级管理员",
          username: "admin"
        },
      ],
      editForm: reactive({
        username: "",
        email: "",
        mobile: "",
        id: "",
      }),
    }
  },

  methods: {
    async getUsers() {
      const res = await api.user.getUsers(this.queryInfo)
      if (res.data.meta.status !== 200) {
        return ElMessage.error("获取用户列表失败！")
      }
      console.log("users:", res.data.data.users)
      this.tableData = res.data.data.users;
      this.total = res.data.data.total;
    },

    handleSizeChange(newSize) {
      this.queryInfo.pagesize = newSize;
      this.getUsers();
    },

    handleCurrentChange(currentPage) {
      this.queryInfo.pagenum = currentPage;
      this.getUsers();
    },

    async change(user) {
      console.log(user)
      const res = await api.user.changeState(user)
      if (res.data.meta.status !== 200) {
        user.mg_state = !user.mg_state
        return ElMessage.error("修改失败！")
      }
      return ElMessage.success("修改成功！")
    },

    async editUserSubmit() {
      const res = await api.user.editUser(this.editForm)
      if (res.data.meta.status !== 200) {
        ElMessage.error("编辑用户失败！")
      }
      await this.getUsers()
      ElMessage.success("编辑用户成功！")
    },

    editUser(user) {
      this.editForm.id = user.id;
      this.editForm.username = user.username;
      this.editForm.email = user.email;
      this.editForm.mobile = user.mobile;
    },

    async deleteUser(user){
      const res = await api.user.deleteUser(user)
      if (res.data.meta.status !== 200) {
        console.log(res)
        return  ElMessage.error("删除用户失败！")
      }
      this.queryInfo.pagenum = 1;
      await this.getUsers()
      ElMessage.success("删除用户成功！")
    }


  },

  mounted() {
    this.getUsers()
  }
}
</script>

<style scoped>

</style>
