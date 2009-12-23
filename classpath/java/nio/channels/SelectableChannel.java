/* Copyright (c) 2008-2009, Avian Contributors

   Permission to use, copy, modify, and/or distribute this software
   for any purpose with or without fee is hereby granted, provided
   that the above copyright notice and this permission notice appear
   in all copies.

   There is NO WARRANTY for this software.  See license.txt for
   details. */

package java.nio.channels;

import java.io.IOException;
import java.nio.ByteBuffer;

public abstract class SelectableChannel implements Channel {
  private SelectionKey key;

  abstract int socketFD();

  public abstract SelectableChannel configureBlocking(boolean v)
    throws IOException;

  public SelectionKey register(Selector selector, int interestOps,
                               Object attachment)
  {
    key = new SelectionKey(this, selector, interestOps, attachment);
    selector.add(key);
    return key;
  }

  public boolean isOpen() {
    return key != null;
  }

  public void close() throws IOException {
    if (key != null) {
      key = null;
    }
  }
}
