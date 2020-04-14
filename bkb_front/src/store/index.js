import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    userName: '',
    userIdentity: '',
    token: '',
    schoolId: '',
    majorId: '',
  },
  mutations: {
    changeUserName(state, userName) {
      state.userName = userName;
    },
    changeUserIdentity(state, userIdentity) {
      state.userIdentity = userIdentity;
    },
    changeToken(state, token) {
      state.token = token;
    },
    changeSchoolId(state, schoolId) {
      state.schoolId = schoolId;
    },
    changeMajorId(state, majorId) {
      state.majorId = majorId;
    }
  },
  actions: {},
  getters: {}
});
export default store
