package uk.co.roteala.javaprocessor.configs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class Web3Configs {

    private final AppConfigs configs;

    @Bean
    public Web3j web3Configurator() {
        log.info("Connecting to:{}", configs.getUrl());
        Web3j web3j = Web3j.build(new HttpService(configs.getUrl()));

        try {
            List<String> accounts = web3j.ethAccounts().send().getAccounts();

            for(String account : accounts) {
                log.info("Account:{}", account);
            }
        }catch (Exception e) {
            log.error("Filed to fetch accounts!");
        }

        return web3j;
    }

    @Bean
    public Credentials walletConfigs() {
        Credentials credentials;
        try {
            credentials = Credentials.create("e76cfc1b22c4653cf8279d39373294abc3b020715311d543051fddef3aaf5e97");
            log.info("Master wallet address:{}", credentials.getAddress());
        } catch (Exception e) {
            credentials = null;
            log.error("Could not fetch wallet!");
        }

        return credentials;
    }
}
