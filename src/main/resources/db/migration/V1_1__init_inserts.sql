
INSERT INTO public.client(client_id, name)	VALUES (1, 'default');

INSERT INTO public.account(account_id, client_id, enabled, password, username) VALUES (1, 1, true, '1', 'sa');

INSERT INTO public.privilege(privilege_id, name) VALUES (1, 'menuRoles');

INSERT INTO public.privilege(privilege_id, name) VALUES (2, 'menuAccounts');

INSERT INTO public.privilege(privilege_id, name) VALUES (3, 'menuQuetionnaires');
