package cauchyma.coocpcycle.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import cauchyma.coocpcycle.IntegrationTest;
import cauchyma.coocpcycle.domain.Association;
import cauchyma.coocpcycle.repository.AssociationRepository;
import cauchyma.coocpcycle.service.dto.AssociationDTO;
import cauchyma.coocpcycle.service.mapper.AssociationMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AssociationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AssociationResourceIT {

    private static final Integer DEFAULT_ID_A = 1;
    private static final Integer UPDATED_ID_A = 2;

    private static final String DEFAULT_NOM_A = "AAAAAAAAAA";
    private static final String UPDATED_NOM_A = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/associations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AssociationRepository associationRepository;

    @Autowired
    private AssociationMapper associationMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAssociationMockMvc;

    private Association association;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Association createEntity(EntityManager em) {
        Association association = new Association().idA(DEFAULT_ID_A).nomA(DEFAULT_NOM_A);
        return association;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Association createUpdatedEntity(EntityManager em) {
        Association association = new Association().idA(UPDATED_ID_A).nomA(UPDATED_NOM_A);
        return association;
    }

    @BeforeEach
    public void initTest() {
        association = createEntity(em);
    }

    @Test
    @Transactional
    void createAssociation() throws Exception {
        int databaseSizeBeforeCreate = associationRepository.findAll().size();
        // Create the Association
        AssociationDTO associationDTO = associationMapper.toDto(association);
        restAssociationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(associationDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Association in the database
        List<Association> associationList = associationRepository.findAll();
        assertThat(associationList).hasSize(databaseSizeBeforeCreate + 1);
        Association testAssociation = associationList.get(associationList.size() - 1);
        assertThat(testAssociation.getIdA()).isEqualTo(DEFAULT_ID_A);
        assertThat(testAssociation.getNomA()).isEqualTo(DEFAULT_NOM_A);
    }

    @Test
    @Transactional
    void createAssociationWithExistingId() throws Exception {
        // Create the Association with an existing ID
        association.setId(1L);
        AssociationDTO associationDTO = associationMapper.toDto(association);

        int databaseSizeBeforeCreate = associationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAssociationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(associationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Association in the database
        List<Association> associationList = associationRepository.findAll();
        assertThat(associationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkIdAIsRequired() throws Exception {
        int databaseSizeBeforeTest = associationRepository.findAll().size();
        // set the field null
        association.setIdA(null);

        // Create the Association, which fails.
        AssociationDTO associationDTO = associationMapper.toDto(association);

        restAssociationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(associationDTO))
            )
            .andExpect(status().isBadRequest());

        List<Association> associationList = associationRepository.findAll();
        assertThat(associationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNomAIsRequired() throws Exception {
        int databaseSizeBeforeTest = associationRepository.findAll().size();
        // set the field null
        association.setNomA(null);

        // Create the Association, which fails.
        AssociationDTO associationDTO = associationMapper.toDto(association);

        restAssociationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(associationDTO))
            )
            .andExpect(status().isBadRequest());

        List<Association> associationList = associationRepository.findAll();
        assertThat(associationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAssociations() throws Exception {
        // Initialize the database
        associationRepository.saveAndFlush(association);

        // Get all the associationList
        restAssociationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(association.getId().intValue())))
            .andExpect(jsonPath("$.[*].idA").value(hasItem(DEFAULT_ID_A)))
            .andExpect(jsonPath("$.[*].nomA").value(hasItem(DEFAULT_NOM_A)));
    }

    @Test
    @Transactional
    void getAssociation() throws Exception {
        // Initialize the database
        associationRepository.saveAndFlush(association);

        // Get the association
        restAssociationMockMvc
            .perform(get(ENTITY_API_URL_ID, association.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(association.getId().intValue()))
            .andExpect(jsonPath("$.idA").value(DEFAULT_ID_A))
            .andExpect(jsonPath("$.nomA").value(DEFAULT_NOM_A));
    }

    @Test
    @Transactional
    void getNonExistingAssociation() throws Exception {
        // Get the association
        restAssociationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAssociation() throws Exception {
        // Initialize the database
        associationRepository.saveAndFlush(association);

        int databaseSizeBeforeUpdate = associationRepository.findAll().size();

        // Update the association
        Association updatedAssociation = associationRepository.findById(association.getId()).get();
        // Disconnect from session so that the updates on updatedAssociation are not directly saved in db
        em.detach(updatedAssociation);
        updatedAssociation.idA(UPDATED_ID_A).nomA(UPDATED_NOM_A);
        AssociationDTO associationDTO = associationMapper.toDto(updatedAssociation);

        restAssociationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, associationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(associationDTO))
            )
            .andExpect(status().isOk());

        // Validate the Association in the database
        List<Association> associationList = associationRepository.findAll();
        assertThat(associationList).hasSize(databaseSizeBeforeUpdate);
        Association testAssociation = associationList.get(associationList.size() - 1);
        assertThat(testAssociation.getIdA()).isEqualTo(UPDATED_ID_A);
        assertThat(testAssociation.getNomA()).isEqualTo(UPDATED_NOM_A);
    }

    @Test
    @Transactional
    void putNonExistingAssociation() throws Exception {
        int databaseSizeBeforeUpdate = associationRepository.findAll().size();
        association.setId(count.incrementAndGet());

        // Create the Association
        AssociationDTO associationDTO = associationMapper.toDto(association);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAssociationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, associationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(associationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Association in the database
        List<Association> associationList = associationRepository.findAll();
        assertThat(associationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAssociation() throws Exception {
        int databaseSizeBeforeUpdate = associationRepository.findAll().size();
        association.setId(count.incrementAndGet());

        // Create the Association
        AssociationDTO associationDTO = associationMapper.toDto(association);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAssociationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(associationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Association in the database
        List<Association> associationList = associationRepository.findAll();
        assertThat(associationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAssociation() throws Exception {
        int databaseSizeBeforeUpdate = associationRepository.findAll().size();
        association.setId(count.incrementAndGet());

        // Create the Association
        AssociationDTO associationDTO = associationMapper.toDto(association);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAssociationMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(associationDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Association in the database
        List<Association> associationList = associationRepository.findAll();
        assertThat(associationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAssociationWithPatch() throws Exception {
        // Initialize the database
        associationRepository.saveAndFlush(association);

        int databaseSizeBeforeUpdate = associationRepository.findAll().size();

        // Update the association using partial update
        Association partialUpdatedAssociation = new Association();
        partialUpdatedAssociation.setId(association.getId());

        partialUpdatedAssociation.idA(UPDATED_ID_A);

        restAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAssociation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAssociation))
            )
            .andExpect(status().isOk());

        // Validate the Association in the database
        List<Association> associationList = associationRepository.findAll();
        assertThat(associationList).hasSize(databaseSizeBeforeUpdate);
        Association testAssociation = associationList.get(associationList.size() - 1);
        assertThat(testAssociation.getIdA()).isEqualTo(UPDATED_ID_A);
        assertThat(testAssociation.getNomA()).isEqualTo(DEFAULT_NOM_A);
    }

    @Test
    @Transactional
    void fullUpdateAssociationWithPatch() throws Exception {
        // Initialize the database
        associationRepository.saveAndFlush(association);

        int databaseSizeBeforeUpdate = associationRepository.findAll().size();

        // Update the association using partial update
        Association partialUpdatedAssociation = new Association();
        partialUpdatedAssociation.setId(association.getId());

        partialUpdatedAssociation.idA(UPDATED_ID_A).nomA(UPDATED_NOM_A);

        restAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAssociation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAssociation))
            )
            .andExpect(status().isOk());

        // Validate the Association in the database
        List<Association> associationList = associationRepository.findAll();
        assertThat(associationList).hasSize(databaseSizeBeforeUpdate);
        Association testAssociation = associationList.get(associationList.size() - 1);
        assertThat(testAssociation.getIdA()).isEqualTo(UPDATED_ID_A);
        assertThat(testAssociation.getNomA()).isEqualTo(UPDATED_NOM_A);
    }

    @Test
    @Transactional
    void patchNonExistingAssociation() throws Exception {
        int databaseSizeBeforeUpdate = associationRepository.findAll().size();
        association.setId(count.incrementAndGet());

        // Create the Association
        AssociationDTO associationDTO = associationMapper.toDto(association);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, associationDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(associationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Association in the database
        List<Association> associationList = associationRepository.findAll();
        assertThat(associationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAssociation() throws Exception {
        int databaseSizeBeforeUpdate = associationRepository.findAll().size();
        association.setId(count.incrementAndGet());

        // Create the Association
        AssociationDTO associationDTO = associationMapper.toDto(association);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(associationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Association in the database
        List<Association> associationList = associationRepository.findAll();
        assertThat(associationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAssociation() throws Exception {
        int databaseSizeBeforeUpdate = associationRepository.findAll().size();
        association.setId(count.incrementAndGet());

        // Create the Association
        AssociationDTO associationDTO = associationMapper.toDto(association);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAssociationMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(associationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Association in the database
        List<Association> associationList = associationRepository.findAll();
        assertThat(associationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAssociation() throws Exception {
        // Initialize the database
        associationRepository.saveAndFlush(association);

        int databaseSizeBeforeDelete = associationRepository.findAll().size();

        // Delete the association
        restAssociationMockMvc
            .perform(delete(ENTITY_API_URL_ID, association.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Association> associationList = associationRepository.findAll();
        assertThat(associationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
