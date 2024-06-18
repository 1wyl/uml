<template>
  <div class="health-record-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>健康档案（只读）</span>
      </div>
      <el-form :model="healthRecord" label-width="120px" class="health-record-form">
        <el-form-item label="姓名">
          <el-input v-model="healthRecord.name" readonly clearable></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-input v-model="healthRecord.gender" readonly clearable></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="healthRecord.age" readonly clearable></el-input>
        </el-form-item>
        <el-form-item label="身高 (米)">
          <el-input v-model="healthRecord.height" readonly clearable></el-input>
        </el-form-item>
        <el-form-item label="体重 (千克)">
          <el-input v-model="healthRecord.weight" readonly clearable></el-input>
        </el-form-item>
        <el-form-item label="血型">
          <el-input v-model="healthRecord.bloodType" readonly clearable></el-input>
        </el-form-item>
        <el-form-item label="过敏史">
          <el-input v-model="healthRecord.allergies" readonly clearable></el-input>
        </el-form-item>
        <el-form-item label="病史">
          <el-input v-model="healthRecord.medicalHistory" readonly clearable></el-input>
        </el-form-item>
        <el-form-item label="当前用药">
          <el-input v-model="healthRecord.medications" readonly clearable></el-input>
        </el-form-item>
        <el-form-item label="吸烟状态">
          <el-input v-model="healthRecord.smokingStatus" readonly clearable></el-input>
        </el-form-item>
        <el-form-item label="饮酒情况">
          <el-input v-model="healthRecord.alcoholUse" readonly clearable></el-input>
        </el-form-item>
        <el-form-item label="体力活动">
          <el-input v-model="healthRecord.physicalActivity" readonly clearable></el-input>
        </el-form-item>
        <el-form-item label="饮食情况">
          <el-input v-model="healthRecord.diet" readonly clearable></el-input>
        </el-form-item>
        <el-form-item label="血压情况">
          <el-input v-model="healthRecord.bloodPressure" readonly clearable></el-input>
        </el-form-item>
        <el-form-item label="胆固醇水平">
          <el-input v-model="healthRecord.cholesterolLevel" readonly clearable></el-input>
        </el-form-item>
        <el-form-item label="血糖水平">
          <el-input v-model="healthRecord.glucoseLevel" readonly clearable></el-input>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { getHealthRecord, updateHealthRecord } from '@/api/healthRecord';

export default {
  data() {
    return {
      healthRecord: {
        uid: null,
        name: '',
        gender: '',
        age: null,
        height: null,
        weight: null,
        bloodType: '',
        allergies: '',
        medicalHistory: '',
        medications: '',
        smokingStatus: '',
        alcoholUse: '',
        physicalActivity: '',
        diet: '',
        bloodPressure: '',
        cholesterolLevel: null,
        glucoseLevel: null
      }
    };
  },
  created() {
    this.fetchHealthRecord();
  },
  methods: {
    fetchHealthRecord() {
        getHealthRecord().then(response => {
          this.healthRecord = response.data;
        }).catch(error => {
          this.$message.error('无法获取健康档案数据');
        });
    }
  }
};
</script>

<style scoped>
.health-record-container {
  padding: 20px;
}

.el-form-item__label {
  font-weight: bold;
}

.el-input,
.el-input-number,
.el-select {
  font-family: Arial, sans-serif;
}
</style>
