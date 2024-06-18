<template>
  <div id="app">
    <!-- <div >
      <br/><br/><br/>

    </div> -->
    <!-- <nav>
      <router-link to="/">Login</router-link> |
      <router-link to="/adduser">adduser</router-link> |
      <router-link to="/userinfo">userinfo</router-link> |
      <router-link to="/todolist">todolist</router-link> |
      <router-link to="/addtodo">addtodo</router-link> |
      <router-link to="/edit-task">edit-task</router-link>
    </nav> -->
    <router-view/>
  </div>
</template>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

// nav {
//   padding: 30px;

//   a {
//     font-weight: bold;
//     color: #2c3e50;

//     &.router-link-exact-active {
//       color: #42b983;
//     }
//   }
// }
</style>
