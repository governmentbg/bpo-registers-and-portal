package bg.duosoft.bpo.registers.config.analyzer;

import org.hibernate.search.backend.elasticsearch.analysis.ElasticsearchAnalysisConfigurationContext;
import org.hibernate.search.backend.elasticsearch.analysis.ElasticsearchAnalysisConfigurer;

/**
 * User: ggeorgiev
 * Date: 28.04.2022
 * Time: 16:58
 */
public class BpoRegistersHibernateSearchElasticSearchAnalysisConfigurer implements ElasticsearchAnalysisConfigurer {
    @Override
    public void configure(ElasticsearchAnalysisConfigurationContext luceneAnalysisConfigurationContext) {

        luceneAnalysisConfigurationContext
                .tokenFilter("bpoRegistersPatternReplace1")
                .type("pattern_replace")
                .param("pattern", "^\\s*")
                .param("replacement", "")
                .param("replace", "all");
        luceneAnalysisConfigurationContext
                .tokenFilter("bpoRegistersPatternReplace2")
                .type("pattern_replace")
                .param("pattern", "[\\„\\\"\\']")
                .param("replacement", "")
                .param("replace", "all");
        luceneAnalysisConfigurationContext
                .tokenFilter("bpoRegistersPatternReplace3")
                .type("pattern_replace")
                .param("pattern", "\\s{2,}")
                .param("replacement", " ")
                .param("replace", "all");
        // Normalize Greek final sigma (ς) to regular sigma (σ) for consistent matching
        // This ensures both variants match during indexing and searching
        luceneAnalysisConfigurationContext
                .tokenFilter("bpoRegistersGreekSigmaNormalizer")
                .type("pattern_replace")
                .param("pattern", "ς")
                .param("replacement", "σ")
                .param("replace", "all");

        luceneAnalysisConfigurationContext
                .tokenizer("semicolon_tokenizer")
                .type("pattern")
                .param("pattern", ";");


        luceneAnalysisConfigurationContext.analyzer("FullTextAnalyzer").custom()
                .tokenizer("keyword")
                .tokenFilters("lowercase", "bpoRegistersGreekSigmaNormalizer", "bpoRegistersPatternReplace1", "bpoRegistersPatternReplace2", "bpoRegistersPatternReplace3");

        luceneAnalysisConfigurationContext
                .normalizer("sortNormalizer")
                .custom()
                .tokenFilters("lowercase", "bpoRegistersGreekSigmaNormalizer", "bpoRegistersPatternReplace1", "bpoRegistersPatternReplace2", "bpoRegistersPatternReplace3");

        luceneAnalysisConfigurationContext.analyzer("SemicolonAnalyzer").custom()
                .tokenizer("semicolon_tokenizer")  // Use the semicolon tokenizer defined above
                .tokenFilters("lowercase", "bpoRegistersGreekSigmaNormalizer", "bpoRegistersPatternReplace1", "bpoRegistersPatternReplace2", "bpoRegistersPatternReplace3");


        luceneAnalysisConfigurationContext.analyzer("WordAnalyzer").custom()
                .tokenizer("standard")
                .tokenFilters("lowercase", "bpoRegistersGreekSigmaNormalizer", "bpoRegistersPatternReplace1", "bpoRegistersPatternReplace2", "bpoRegistersPatternReplace3");
    }
}
