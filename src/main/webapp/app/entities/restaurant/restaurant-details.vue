<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="restaurant">
        <h2 class="jh-entity-heading" data-cy="restaurantDetailsHeading">
          <span v-text="$t('coopcycleApp.restaurant.detail.title')">Restaurant</span> {{ restaurant.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('coopcycleApp.restaurant.nom')">Nom</span>
          </dt>
          <dd>
            <span>{{ restaurant.nom }}</span>
          </dd>
          <dt>
            <span v-text="$t('coopcycleApp.restaurant.adresse')">Adresse</span>
          </dt>
          <dd>
            <span>{{ restaurant.adresse }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="restaurant.id"
          :to="{ name: 'RestaurantEdit', params: { restaurantId: restaurant.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./restaurant-details.component.ts"></script>
