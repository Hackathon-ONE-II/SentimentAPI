package com.hackathon.SentimentAPI.service;

import com.hackathon.SentimentAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Serviço responsável pela autenticação de usuários.
 *
 * <p>
 * Esta classe implementa {@link UserDetailsService}, sendo utilizada pelo
 * Spring Security durante o processo de autenticação para carregar
 * os dados do usuário a partir do banco de dados.
 * </p>
 *
 * <p>
 * O método {@link #loadUserByUsername(String)} é chamado automaticamente
 * pelo Spring Security quando um usuário tenta se autenticar.
 * </p>
 */
@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    /**
     * Carrega um usuário com base no username informado.
     *
     * @param username nome de usuário utilizado no processo de autenticação
     * @return {@link UserDetails} contendo as informações do usuário
     * @throws UsernameNotFoundException caso o usuário não seja encontrado
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }
}
