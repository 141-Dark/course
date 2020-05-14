module.exports = {
  root:true,
  'env': {
    'browser': true,
    'commonjs': true,
    'node': true,
    'es6': true
  },

  'extends':[
      'plugin:vue/essential',
      'eslint:recommended'
  ],
  'rules': {

    'no-console':process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'no-debugger':process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'no-undef':'off',
    'vue/no-unused-vars':'off',
    'vue/require-v-for-key':'off',
  },
  parserOptions: {
    parser:'babel-eslint'
  }
};
