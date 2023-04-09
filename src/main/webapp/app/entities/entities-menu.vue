<template>
  <div>
    <b-dropdown-item to="/client">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.client')">Client</span>
    </b-dropdown-item>
    <b-dropdown-item to="/commande">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.commande')">Commande</span>
    </b-dropdown-item>
    <b-dropdown-item to="/restaurant">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.restaurant')">Restaurant</span>
    </b-dropdown-item>
    <b-dropdown-item to="/produit">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.produit')">Produit</span>
    </b-dropdown-item>
    <b-dropdown-item to="/livreur">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.livreur')">Livreur</span>
    </b-dropdown-item>
    <b-dropdown-item to="/association">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.association')">Association</span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
