package org.skypro.skyshop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exceptions.BestResultNotFound;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;
    @Test
    void testSearch_WhenStorageIsEmpty() {
        when(storageService.getSearchables())
                .thenReturn(Collections.emptyList());

        Assertions.assertThrows(
                BestResultNotFound.class,
                () -> searchService.search("apple")
        );
    }
    @Test
    void testSearch_NoMatchingObject() {
        Searchable s1 = mock(Searchable.class);
        when(s1.getSearchableName()).thenReturn("banana");

        when(storageService.getSearchables())
                .thenReturn(List.of(s1));

        Assertions.assertThrows(
                BestResultNotFound.class,
                () -> searchService.search("apple")
        );
    }
    @Test
    void testSearch_FoundObject() throws BestResultNotFound {
        Searchable searchable = mock(Searchable.class);

        UUID id = UUID.randomUUID();

        when(searchable.getId()).thenReturn(id);
        when(searchable.getSearchableName()).thenReturn("apple");
        when(searchable.type()).thenReturn("product");

        when(storageService.getSearchables())
                .thenReturn(List.of(searchable));

        SearchResult result = searchService.search("apple");

        Assertions.assertEquals(id, result.getId());
        Assertions.assertEquals("apple", result.getName());
    }

    @Test
    void testSearch_VerifyInteraction() {
        when(storageService.getSearchables())
                .thenReturn(Collections.emptyList());

        Assertions.assertThrows(
                BestResultNotFound.class,
                () -> searchService.search("test")
        );

        verify(storageService, times(1)).getSearchables();
    }
}
