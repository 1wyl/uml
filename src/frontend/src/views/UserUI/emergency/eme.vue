<template>
  <div class="emergency-button-container">
    <div class="emergency-button" @click="handleClick">
      紧急按钮
    </div>
  </div>
</template>

<script>
import { fetchhistory } from '@/api/emer'; // 确保路径正确

export default {
  data() {
    return {
      location: null, // 设备点击按钮时的地理位置
      realtimeData: null // 实时数据
    };
  },
  created() {
    this.fetchRealtimeData(); // 在组件创建后立即获取实时数据
  },
  methods: {
    handleClick() {
      // 如果浏览器支持地理位置 API
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          position => {
            // 获取地理位置成功
            this.location = {
              latitude: position.coords.latitude, // 纬度
              longitude: position.coords.longitude // 经度
            };
            // 点击按钮后发送请求
            this.sendEmergencyRequest();
          },
          error => {
            // 获取地理位置失败
            console.error('Error getting geolocation:', error);
            this.$message.error('获取地理位置失败，请重试');
          }
        );
      } else {
        // 浏览器不支持地理位置 API
        this.$message.error('您的浏览器不支持地理位置服务');
      }
    },
    sendEmergencyRequest() {
      // 如果成功获取到了地理位置信息，则发送紧急请求
      if (this.location) {
        this.$confirm('是否确认发送紧急请求?', '确认发送', {
          confirmButtonText: '确认发送',
          cancelButtonText: '取消发送',
          type: 'warning'
        }).then(() => {
          fetchhistory(this.location)
            .then(response => {
              this.$message({
                type: 'success',
                message: '紧急请求已发送'
              });
              // 发送请求后再次获取实时数据
              // this.fetchRealtimeData();
            })
            .catch(error => {
              this.$message({
                type: 'error',
                message: '发送紧急请求失败'
              });
              console.error('Error sending emergency request:', error);
            });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '紧急请求已取消'
          });
        });
      } else {
        this.$message.error('未能获取到地理位置，请确认您的浏览器设置或重试');
      }
    },
    fetchRealtimeData() {
      // 发送请求以获取实时数据
      // 这里假设实时数据获取的 API 为 fetchRealtimeData
      // 你需要根据你的实际情况进行调整
      fetchRealtimeData()
        .then(response => {
          this.realtimeData = response.data; // 更新实时数据
        })
        .catch(error => {
          console.error('Error fetching realtime data:', error);
        });
    }
  }
};
</script>

<style scoped>
.emergency-button-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh; /* 使容器充满整个视窗高度 */
  margin-top: -100px;
}

.emergency-button {
  width: 200px;
  height: 200px;
  background-color: red;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  font-size: 24px;
  font-weight: bold;
  cursor: pointer;
  user-select: none;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
  transition: transform 0.2s;
}

.emergency-button:hover {
  transform: scale(1.1);
}
</style>
