import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    userName: '',
    userIdentity: '',
    token: '',
    schoolName:'',
    schoolId: '',
    schoolCity:'',
    majorId: '',
    majorName:'',
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
    changeSchoolName(state,schoolName){
      state.schoolName = schoolName;
    },
    changeSchoolId(state, schoolId) {
      state.schoolId = schoolId;
    },
    changeSchoolCity(state,schoolCity){
      state.schoolCity = schoolCity;
    },
    changeMajorId(state, majorId) {
      state.majorId = majorId;
    },
    changeMajorName(state, majorName) {
      state.majorName = majorName;
    }
  },
  actions: {},
  getters: {}
});
export default store
