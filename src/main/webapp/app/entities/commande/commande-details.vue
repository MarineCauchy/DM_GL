<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="commande">
        <h2 class="jh-entity-heading" data-cy="commandeDetailsHeading">
          <span v-text="$t('coopcycleApp.commande.detail.title')">Commande</span> {{ commande.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('coopcycleApp.commande.idCo')">Id Co</span>
          </dt>
          <dd>
            <span>{{ commande.idCo }}</span>
          </dd>
          <dt>
            <span v-text="$t('coopcycleApp.commande.prixTotal')">Prix Total</span>
          </dt>
          <dd>
            <span>{{ commande.prixTotal }}</span>
          </dd>
          <dt>
            <span v-text="$t('coopcycleApp.commande.date')">Date</span>
          </dt>
          <dd>
            <span v-if="commande.date">{{ $d(Date.parse(commande.date), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('coopcycleApp.commande.adresseLivraison')">Adresse Livraison</span>
          </dt>
          <dd>
            <span>{{ commande.adresseLivraison }}</span>
          </dd>
          <dt>
            <span v-text="$t('coopcycleApp.commande.client')">Client</span>
          </dt>
          <dd>
            <div v-if="commande.client">
              <router-link :to="{ name: 'ClientView', params: { clientId: commande.client.id } }">{{ commande.client.id }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('coopcycleApp.commande.livreur')">Livreur</span>
          </dt>
          <dd>
            <div v-if="commande.livreur">
              <router-link :to="{ name: 'LivreurView', params: { livreurId: commande.livreur.id } }">{{ commande.livreur.id }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="commande.id" :to="{ name: 'CommandeEdit', params: { commandeId: commande.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./commande-details.component.ts"></script>
