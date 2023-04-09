<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="produit">
        <h2 class="jh-entity-heading" data-cy="produitDetailsHeading">
          <span v-text="$t('coopcycleApp.produit.detail.title')">Produit</span> {{ produit.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('coopcycleApp.produit.nom')">Nom</span>
          </dt>
          <dd>
            <span>{{ produit.nom }}</span>
          </dd>
          <dt>
            <span v-text="$t('coopcycleApp.produit.prix')">Prix</span>
          </dt>
          <dd>
            <span>{{ produit.prix }}</span>
          </dd>
          <dt>
            <span v-text="$t('coopcycleApp.produit.commande')">Commande</span>
          </dt>
          <dd>
            <div v-if="produit.commande">
              <router-link :to="{ name: 'CommandeView', params: { commandeId: produit.commande.id } }">{{
                produit.commande.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('coopcycleApp.produit.client')">Client</span>
          </dt>
          <dd>
            <div v-if="produit.client">
              <router-link :to="{ name: 'ClientView', params: { clientId: produit.client.id } }">{{ produit.client.id }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('coopcycleApp.produit.restaurant')">Restaurant</span>
          </dt>
          <dd>
            <div v-if="produit.restaurant">
              <router-link :to="{ name: 'RestaurantView', params: { restaurantId: produit.restaurant.id } }">{{
                produit.restaurant.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="produit.id" :to="{ name: 'ProduitEdit', params: { produitId: produit.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./produit-details.component.ts"></script>
