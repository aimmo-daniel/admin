package sj.jpa.admin.interfaces;

import sj.jpa.admin.model.network.Header;

public interface CrudInterface<Request, Response> {

    Header<Response> create(Header<Request> request);

    Header<Response> read(Long id);

    Header<Response> update(Header<Request> request);

    Header<Response> delete(Long id);

}
