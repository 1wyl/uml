<template>
  <div class="health-record-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>{{ healthRecord.name }}的健康档案</span>
        <div style="float: right;">
          <el-button @click="back" style="margin-right: 10px;">返回</el-button>
          <el-button type="primary" @click="editMode = !editMode">
            {{ editMode ? '取消编辑' : '编辑' }}
          </el-button>
        </div>
      </div>

      <el-form :model="healthRecord" label-width="120px" class="health-record-form">
        <el-form-item label="身高 (厘米)">
          <el-input-number v-model="healthRecord.height" :disabled="!editMode" :step="0.01" :min="0"></el-input-number>
        </el-form-item>
        <el-form-item label="体重 (斤)">
          <el-input-number v-model="healthRecord.weight" :disabled="!editMode" :step="0.1" :min="0"></el-input-number>
        </el-form-item>
        <el-form-item label="血型">
          <el-select v-model="healthRecord.bloodType" :disabled="!editMode">
            <el-option label="A型" value="A"></el-option>
            <el-option label="B型" value="B"></el-option>
            <el-option label="AB型" value="AB"></el-option>
            <el-option label="O型" value="O"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="过敏史">
          <el-input v-model="healthRecord.allergies" :disabled="!editMode"></el-input>
        </el-form-item>
        <el-form-item label="病史">
          <el-input v-model="healthRecord.medicalHistory" :disabled="!editMode"></el-input>
        </el-form-item>
        <el-form-item label="当前用药">
          <el-input v-model="healthRecord.medications" :disabled="!editMode"></el-input>
        </el-form-item>
        <el-form-item label="吸烟状态">
          <el-select v-model="healthRecord.smokingStatus" :disabled="!editMode">
            <el-option label="不吸烟" value="不吸烟"></el-option>
            <el-option label="吸烟" value="吸烟"></el-option>
            <el-option label="曾经吸烟" value="曾经吸烟"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="饮酒情况">
          <el-select v-model="healthRecord.alcoholUse" :disabled="!editMode">
            <el-option label="不饮酒" value="不饮酒"></el-option>
            <el-option label="偶尔饮酒" value="偶尔饮酒"></el-option>
            <el-option label="经常饮酒" value="经常饮酒"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="体力活动">
          <el-select v-model="healthRecord.physicalActivity" :disabled="!editMode">
            <el-option label="无" value="无"></el-option>
            <el-option label="轻度" value="轻度"></el-option>
            <el-option label="中度" value="中度"></el-option>
            <el-option label="重度" value="重度"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="饮食情况">
          <el-input v-model="healthRecord.diet" :disabled="!editMode"></el-input>
        </el-form-item>
        <el-form-item label="血压情况">
          <el-input v-model="healthRecord.bloodPressure" :disabled="!editMode"></el-input>
        </el-form-item>
        <el-form-item label="胆固醇水平">
          <el-input-number v-model="healthRecord.cholesterolLevel" :disabled="!editMode" :step="0.1" :min="0"></el-input-number>
        </el-form-item>
        <el-form-item label="血糖水平">
          <el-input-number v-model="healthRecord.glucoseLevel" :disabled="!editMode" :step="0.1" :min="0"></el-input-number>
        </el-form-item>
        <el-form-item v-if="editMode">
          <el-button type="primary" @click="saveHealthRecord">保存</el-button>

        </el-form-item>

      </el-form>
    </el-card>
  </div>
</template>

<script>
import { getHealthRecordbyUid, updateHealthRecord } from '@/api/healthRecord';

export default {
  data() {
    return {
      editMode: false,
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
      const uid = this.$route.query.uid;
      if (uid) {
        getHealthRecordbyUid(uid).then(response => {
          this.healthRecord = response.data;
        }).catch(error => {
          this.$message.error('无法获取健康档案数据');
        });
      } else {
        this.$message.error('用户ID未提供');
      }
    },
    saveHealthRecord() {
      const uid = this.$route.query.uid;
      updateHealthRecord(uid, this.healthRecord).then(() => {
        this.$message.success('健康档案更新成功');
        this.editMode = false;
        this.$router.go(-1)
      }).catch(error => {
        this.$message.error('更新健康档案失败');
      });
    },
    back(){
      this.$router.go(-1)
  }
  }
};
</script>

<style scoped>
.health-record-container {
  padding: 20px;
}
</style>
