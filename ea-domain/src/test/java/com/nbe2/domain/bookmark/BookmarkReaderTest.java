package com.nbe2.domain.bookmark;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BookmarkReaderTest {

	@InjectMocks
	private BookmarkReader bookmarkReader;

	@Mock
	private BookmarkRepository bookmarkRepository;


	private Bookmark expectedBookmark;
	private long userId;
	private long emergencyRoomId;

	@BeforeEach
	void setUp(){
		MockitoAnnotations.openMocks(this);
		expectedBookmark = BookmarkFixture.createBookmark();
		emergencyRoomId = 1L;

	}

	@Test
	@DisplayName("유저 ID를 통해 즐겨찾기 목록를 조회한다.")
	void given_userId_when_read_bookmark_then_should_return_bookmarkList(){
		//given
		userId = 1L;
		List<Bookmark> expectedBookmarks = List.of(expectedBookmark);

		//when
		when(bookmarkRepository.findByUserId(userId)).thenReturn(expectedBookmarks);
		List<Bookmark> actualBookmarks = bookmarkReader.readByUserId(expectedBookmark.getUser().getId());

		//then
		assertThat(actualBookmarks).isEqualTo(expectedBookmarks);
		verify(bookmarkRepository, times(1)).findByUserId(userId);
	}

	@Test
	@DisplayName("유저ID와 응급실 ID를 통해 즐겨찾기된 응급실을 조회한다.")
	void given_userId_emergencyRoomId_When_read_bookmark_then_should_return_bookmark(){
		//given
		userId = 1L;
		emergencyRoomId = 1L;
		Bookmark expectedBookmark = BookmarkFixture.createBookmark();
		//when
		when(bookmarkRepository.findByEmergencyRoomIdAndUserId(emergencyRoomId, userId)).thenReturn(
				Optional.of(expectedBookmark));

		//then
		Bookmark actualBookmark = bookmarkReader.readByEmergencyRoomIdAndUserId(emergencyRoomId, userId);
		assertEquals(expectedBookmark,actualBookmark);
	}

}