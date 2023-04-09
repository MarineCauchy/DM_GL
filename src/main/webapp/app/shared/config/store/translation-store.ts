import { Module } from 'vuex';

export const translationStore: Module<any, any> = {
  state: {
    currentLanguage: localStorage.getItem('currentLanguage') || 'fr',
    languages: {
      'ar-ly': { name: 'العربية', rtl: true },
      by: { name: 'Беларускі' },
      'zh-cn': { name: '中文（简体）' },
      en: { name: 'English' },
      et: { name: 'Eesti' },
      fa: { name: 'فارسی', rtl: true },
      fr: { name: 'Français' },
      ua: { name: 'Українська' },
      'uz-Cyrl-uz': { name: 'Ўзбекча' },
      vi: { name: 'Tiếng Việt' },
      // jhipster-needle-i18n-language-key-pipe - JHipster will add/remove languages in this object
    },
  },
  getters: {
    currentLanguage: state => state.currentLanguage,
    languages: state => state.languages,
  },
  mutations: {
    currentLanguage(state, newLanguage) {
      state.currentLanguage = newLanguage;
      localStorage.setItem('currentLanguage', newLanguage);
    },
  },
};
