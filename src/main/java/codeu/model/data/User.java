// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.



package codeu.model.data;

import java.time.Instant;
import java.util.UUID;

/** Class representing a registered user. */
public class User {
  private final UUID id;
  private final String name;
  private final String hashedPassword;
  private final Instant creation;
  private String profile;
  private final boolean admin;

  /**
   * Constructs a new User.
   *
   * @param id the ID of this User
   * @param name the username of this User
   * @param password the password of this User
   * @param creation the creation time of this User
   * @param admin the admin property of this User
   */
  public User(UUID id, String name, String password, String profile, Instant creation, boolean admin) {
    this.id = id;
    this.name = name;
    this.hashedPassword = password;
    this.creation = creation;
    this.profile = profile;
    this.admin = admin;
  }

  /** Returns the ID of this User. */
  public UUID getId() {
    return id;
  }

  /** Returns the username of this User. */
  public String getName() {
    return name;
  }

  /** Returns the hashed password of this User. */
  public String getPassword() {
    return hashedPassword;
  }

  /** Returns the creation time of this User. */
  public Instant getCreationTime() {
    return creation;
  }

  /** Returns the profile content of this User. */
  public String getProfile() {
    return profile;
  }

  /** Allows the user to set profile content. */
  public void setProfile(String profile) {
    this.profile = profile;
  }

  /** Returns the admin property of this User. */
  public boolean getAdmin() {
    return admin;
  }
}
