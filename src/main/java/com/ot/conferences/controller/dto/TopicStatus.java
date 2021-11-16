package com.ot.conferences.controller.dto;

/**
 * Statuses witch can be used for topic
 *
 * @author O.Trusova
 *
 */

public enum TopicStatus {
    APPROVED,
    PENDING_MODERATOR_APPROVE,
    PENDING_SPEAKER_APPROVE,
    CREATED;
}
