/*
 * Copyright (C) 2015 John Leacox
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.leacox.motif;

/**
 * An exception that indicates that a match could not be found while executing the pattern matching
 * cases.
 *
 * <p>If this exception is thrown it means the matching cases weren't exhaustive. There may be a
 * case that needs to be added to the pattern matching that failed.
 *
 * @author John Leacox
 */
public class MatchException extends RuntimeException {
  public MatchException(String message) {
    super(message);
  }
}
