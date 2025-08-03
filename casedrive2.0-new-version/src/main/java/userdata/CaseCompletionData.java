package userdata;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class CaseCompletionData {
    @Getter
    @RequiredArgsConstructor
    public enum caseDetails {
        CASE_DETAILS(
                "1234",
                "estimation notes Test"
        );
        private final String pageNumber;
        private final String estimateNote;
    }
}